package com.example.discoding


import android.app.Activity
import android.graphics.DiscretePathEffect
import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import com.android.billingclient.api.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BillingModule (
    private val activity: Activity,
    private val lifeCycleScope: LifecycleCoroutineScope,
    private val callback: Callback
) {
    //소비되어야하는 sku
    private val consumableSkus = setOf(Sku.~) ////////////////////////////필요한 sku 만들고 여기 채우기

    //구매관련 업데이트 수신
    private val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
        when {
            billingResult. responseCode == BillingClient.BillingResponseCode.OK && purchases != null -> {
                //구매 완료. 3일 이내 구매확인이 없으면 자동으로 환불?
                for (purchase in purchases) {
                    confirmPurchase(purchase)
                }
            }
            else -> {
                //구매 실패
                callback.onFailure(billingResult.responseCode)
            }
        }
    }

    private var billingClient: BillingClient = BillingClient.newBuilder(activity)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()

    init {
        billingClient.startConnection(object: BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    //billingclient 활성화
                    callback.onBillingModulesIsReady()
                } else {
                    callback.onFailure(billingResult.responseCode)
                }
            }

            override fun onBillingServiceDisconnected() {
                Log.e("BillingModule", "Disconnected.")
            }
        })
    }

    fun onResume(type: String) {
        if (billingClient.isReady) {
            billingClient.queryPurchases(type).purchasesList?.let { purchasesList ->
                for (purchase in purchasesList) {
                    if (!purchase.isAcknowledged && purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                        confirmPurchase(purchase)
                    }
                }
            }
        }
    }

    fun querySkuDetail(
        type: String = BillingClient.SkuType.INAPP,
        vararg sku: String, // sku 목록
        resultBlock: (List<SkuDetails>) -> Unit = {} // sku 상품정보 콜백
    ) {
        SkuDetailsParams.newBuilder().apply {
            setSkusList(sku.asList()).setType(type)

            lifeCycleScope.launch(Dispatchers.IO){
                val skuDetailResult = billingClient.querySkuDetails(build())
                withContext(Dispatchers.Main) {
                    resultBlock(skuDetailResult.skuDetailsList ?: emptyList())
                }
            }
        }
    }

    //구매 시작
   fun purchase(
        skuDetail: SkuDetails // skuDetail : 구매하고자하는 항목 querySkuDetail()을 통해 획득한 SkuDetail
    ) {
        val flowParams = BillingFlowParams.newBuilder().apply {
            setSkuDetails((skuDetail))
        }.build()

        val responseCode = billingClient.launchBillingFlow(activity, flowParams).responseCode
        if (responseCode != BillingClient.BillingResponseCode.OK) {
            callback.onFailure(responseCode)
        }
    }

    //구매 확인 처리
    private fun confirmPurchase(purchase: Purchase) {
        when {
            consumableSkus.contains(purchase.sku) -> {
                //소비성 구매 -> consume
                val consumeParams = ConsumeParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                    .build()

                lifeCycleScope.launch(Dispatchers.IO) {
                    val result = billingClient.consumePurchase(consumeParams)
                    withContext(Dispatchers.Main) {
                        if (result.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                            callback.onSuccess(purchase)
                        }
                    }
                }
            }
            purchase.purchaseState == Purchase.PurchaseState.PURCHASED&& !purchase.isAcknowledged -> {
                //구매가 완료 되었는데 확인이 안되었을 때 구매 확인 처리
                val ackPurchaseParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                lifeCycleScope.launch(Dispatchers.IO) {
                    val result = billingClient.acknowledgePurchase(ackPurchaseParams.build())
                    withContext(Dispatchers.Main) {
                        if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                            callback.onSuccess(purchase)
                        } else {
                            callback.onFailure(result.responseCode)
                        }
                    }
                }
            }
        }
    }

    interface Callback {
        fun onBillingModulesIsReady()
        fun onSuccess(purchase: Purchase)
        fun onFailure(errorCode: Int)
    }
}