package com.students.qb365.ui.auth.model

import com.google.gson.annotations.SerializedName

data class Customers(
    @SerializedName("id") var id: String? = null,
    @SerializedName("object") var object_: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("balance") var balance: Int? = null,
    @SerializedName("created") var created: Int? = null,
    @SerializedName("currency") var currency: String? = null,
    @SerializedName("default_source") var defaultSource: String? = null,
    @SerializedName("delinquent") var delinquent: Boolean? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("discount") var discount: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("invoice_prefix") var invoicePrefix: String? = null,
    @SerializedName("invoice_settings") var invoiceSettings: InvoiceSettings? = InvoiceSettings(),
    @SerializedName("livemode") var livemode: Boolean? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("next_invoice_sequence") var nextInvoiceSequence: Int? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("preferred_locales") var preferredLocales: ArrayList<String> = arrayListOf(),
    @SerializedName("shipping") var shipping: String? = null,
    @SerializedName("tax_exempt") var taxExempt: String? = null,
    @SerializedName("test_clock") var testClock: String? = null
)


data class InvoiceSettings(
    @SerializedName("custom_fields") var customFields: String? = null,
    @SerializedName("default_payment_method") var defaultPaymentMethod: String? = null,
    @SerializedName("footer") var footer: String? = null,
    @SerializedName("rendering_options") var renderingOptions: String? = null
)


