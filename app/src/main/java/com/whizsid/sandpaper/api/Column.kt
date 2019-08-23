package com.whizsid.sandpaper.api

class Column {
    var index:Int = 0

    var width:Int = 0

    private var workspace:Workspace? = null

    fun setWorkspace(workspace: Workspace):void{
        this.workspace = workspace

        return null
    }
}