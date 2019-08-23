package com.whizsid.sandpaper.api

open class Panel:Main() {
    protected open var opened:Boolean = true

    protected open var activeColumn:Int = 0

    open fun open():void{
        this.opened = true

        return null
    }

    open fun close():void{
        this.opened = false

        return null
    }


}