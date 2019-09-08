package com.whizsid.sandpaper.api

class Row {
	var index:Int = 0

    var height:Int = 0

    private var column:Column? = null

    fun setColumn(column: Column):void{
        this.column = column

        return null
    }
}
