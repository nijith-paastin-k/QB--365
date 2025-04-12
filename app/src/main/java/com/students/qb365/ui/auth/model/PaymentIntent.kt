package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class PaymentIntent(
    @SerializedName("id") var id: String? = null,
    @SerializedName("object") var object_: String? = null,
    @SerializedName("amount") var amount: Int? = null,
    @SerializedName("amount_capturable") var amountCapturable: Int? = null,
    @SerializedName("amount_received") var amountReceived: Int? = null,
    @SerializedName("application") var application: String? = null,
    @SerializedName("application_fee_amount") var applicationFeeAmount: String? = null,
    @SerializedName("automatic_payment_methods") var automaticPaymentMethods: AutomaticPaymentMethods? = AutomaticPaymentMethods(),
    @SerializedName("canceled_at") var canceledAt: String? = null,
    @SerializedName("cancellation_reason") var cancellationReason: String? = null,
    @SerializedName("capture_method") var captureMethod: String? = null,
    @SerializedName("charges") var charges: Charges? = Charges(),
    @SerializedName("client_secret") var clientSecret: String? = null,
    @SerializedName("confirmation_method") var confirmationMethod: String? = null,
    @SerializedName("created") var created: Int? = null,
    @SerializedName("currency") var currency: String? = null,
    @SerializedName("customer") var customer: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("invoice") var invoice: String? = null,
    @SerializedName("last_payment_error") var lastPaymentError: String? = null,
    @SerializedName("livemode") var livemode: Boolean? = null,
    @SerializedName("next_action") var nextAction: String? = null,
    @SerializedName("on_behalf_of") var onBehalfOf: String? = null,
    @SerializedName("payment_method") var paymentMethod: String? = null,
    @SerializedName("payment_method_options") var paymentMethodOptions: PaymentMethodOptions? = PaymentMethodOptions(),
    @SerializedName("payment_method_types") var paymentMethodTypes: ArrayList<String> = arrayListOf(),
    @SerializedName("processing") var processing: String? = null,
    @SerializedName("receipt_email") var receiptEmail: String? = null,
    @SerializedName("review") var review: String? = null,
    @SerializedName("setup_future_usage") var setupFutureUsage: String? = null,
    @SerializedName("shipping") var shipping: String? = null,
    @SerializedName("source") var source: String? = null,
    @SerializedName("statement_descriptor") var statementDescriptor: String? = null,
    @SerializedName("statement_descriptor_suffix") var statementDescriptorSuffix: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("transfer_data") var transferData: String? = null,
    @SerializedName("transfer_group") var transferGroup: String? = null

)


data class AutomaticPaymentMethods(

    @SerializedName("enabled") var enabled: Boolean? = null

)


data class Charges(

    @SerializedName("object") var object_: String? = null,
    @SerializedName("data") var data: ArrayList<String> = arrayListOf(),
    @SerializedName("has_more") var hasMore: Boolean? = null,
    @SerializedName("total_count") var totalCount: Int? = null,
    @SerializedName("url") var url: String? = null

)


data class Card(

    @SerializedName("installments") var installments: String? = null,
    @SerializedName("mandate_options") var mandateOptions: String? = null,
    @SerializedName("network") var network: String? = null,
    @SerializedName("request_three_d_secure") var requestThreeDSecure: String? = null

)


data class PaymentMethodOptions(

    @SerializedName("card") var card: Card? = Card()

)
