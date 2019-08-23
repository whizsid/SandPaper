package com.whizsid.sandpaper.api

class Workspace {
    private var columns:MutableList<Column> = mutableListOf()

    private var activeColumn:Column? = null

    private fun index():void{
        var i =0

        this.columns.forEach {
                otherColumn->otherColumn.index=i++
        }

        return null
    }

    fun changeColumn(column:Column):void{
        this.activeColumn = column

        return null
    }

    fun addColumn(column:Column,index:Int = 0):void{
        this.columns.add(index,column)

        // Indexing again
        this.index()

        // Changing width of the columns
        if(index>0){
            val leftColumn = this.columns[index-1]

            leftColumn.width /= 2

            column.width = leftColumn.width
        } else {
            val rightColumn = this.columns[index+1]

            rightColumn.width/=2

            column.width = rightColumn.width
        }

        return null
    }

    fun removeColumn(column:Column):void{

        // Not removing if one and only column
        if(this.columns.size==1)
            return null

        // Removing the column
        this.columns.removeAt(column.index)

        // Adjusting the width of other columns
        if(column.index!=0){
            val leftColumn = this.columns[column.index-1]
            leftColumn.width += column.width
        } else {
            val rightColumn = this.columns[column.index+1]
            rightColumn.width += column.width
        }

        // Indexing again
        this.index()

        return null
    }

    fun shrinkLeft(column:Column,value:Int):void{

    }

}