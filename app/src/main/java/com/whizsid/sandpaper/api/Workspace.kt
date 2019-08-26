package com.whizsid.sandpaper.api

import com.whizsid.sandpaper.api.enums.Direction

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

    /**
     * Change the active column
     *
     * @param column Column instance to active
     */
    fun changeColumn(column:Column):void{
        this.activeColumn = column

        return null
    }

    /**
     * Adding a column
     *
     * @param column Column to add
     * @param index Place of the new column
     */
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

    /**
     * Removing a column
     *
     * @param column
     *
     * @return Weather that column is removed or not
     */
    fun removeColumn(column:Column):Boolean{

        // Not removing if one and only column
        if(this.columns.size==1)
            return false

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

        return true
    }

    /**
     * Shrink a column
     *
     * This function will shrink one column and adding free space to sibling column
     *
     * @param column Column to shrink
     * @param value amount of shrinking
     * @param direction Direction that shrinking
     *
     * @return Originally shrank amount
     */
    fun shrink(column:Column,value:Int,direction: Direction):Int{
        var shrink: Int

        if(direction == Direction.RIGHT){

            if(column.index==this.columns.size-1){
                return 0
            }

            val rightColumn = this.columns[column.index+1]

            if(column.width-value<=1){

                shrink = column.width -1

                column.width = 1

                rightColumn.width += shrink

            } else {

                rightColumn.width += value
                column.width -= value

                shrink = value
            }

            return shrink

        } else if( direction == Direction.LEFT) {

            if(column.index==0){
                return 0
            }

            val leftColumn = this.columns[column.index-1]

            if(leftColumn.width-value<=1) {
                shrink = leftColumn.width - 1

                leftColumn.width = 1
                column.width += shrink

            } else {

                leftColumn.width -= value
                column.width += value

                shrink = value
            }

            return shrink

        }

        return 0
    }

}