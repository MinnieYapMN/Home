package com.example.home

class DatabaseModel{
    var name = ""
    var pNo = 0
    var numG = 0
    var custR = ""
    var bDay = ""
    var tCost= ""

    constructor() {

    }
    constructor(name:String,pNo:Int,numG:Int,custR:String,bDay:String,tCost:String) {
        this.name = name
        this.pNo = pNo
        this.numG = numG
        this.custR = custR
        this.bDay = bDay
        this.tCost = tCost
    }
}