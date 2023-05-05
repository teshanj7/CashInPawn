package com.example.pawningsystem.models

data class PawningModel(
    var psId: String? = null,
    var psName: String? = null,
    var psNIC: String? = null,
    var psTeleNo: String? = null,
    var psBankNo: String? = null,
    var psAddress: String? = null,
    var psPawnItem: String? = null,
    var psEstValue: String? = null
){}