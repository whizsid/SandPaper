package com.whizsid.sandpaper.api

class Column {
    var index:Int = 0

    var width:Int = 0

    private var workspace:Workspace? = null

    fun setWorkspace(workspace: Workspace):void{
        this.workspace = workspace

        return null
    }
    
    private var rows:MutableList<Row> = mutableListOf()

    private var activeRow:Row? = null

    private fun index():void{
        var i =0

        this.rows.forEach {
                otherRow->otherRow.index=i++
        }

        return null
    }

    /**
     * Change the active row
     *
     * @param row instance to active
     */
    fun changeRow(row:Row):void{
        this.activeRow = row

        return null
    }

    /**
     * Adding a row
     *
     * @param row to add
     * @param index Place of the new row
     */
    fun addRow(row:Row,index:Int = 0):void{
        this.rows.add(index,row)

        row.setColumn(this)

        // Indexing again
        this.index()

        // Changing width of the rows
        if(index>0){
            val topRow = this.rows[index-1]

            topRow.height /= 2

            row.height = topRow.height
        } else {
            val bottomRow = this.rows[index+1]

            bottomRow.height/=2

            row.height = bottomRow.height
        }

        return null
    }

    /**
     * Removing a row
     *
     * @param row
     *
     * @return Weather that column is removed or not
     */
    fun removeRow(row:Row):Boolean{

        // Not removing if one and only row
        if(this.rows.size==1)
            return false

        // Removing the row
        this.rows.removeAt(column.index)

        // Adjusting the width of other row
        if(row.index!=0){
            val topRow = this.rows[row.index-1]
            topRow.height += row.height
        } else {
            val bottomRow = this.rows[row.index+1]
            bottomRow.height += row.height
        }

        // Indexing again
        this.index()

        return true
    }

    /**
     * Shrink a row
     *
     * This function will shrink one row and adding free space to sibling row
     *
     * @param row Row to shrink
     * @param value amount of shrinking
     * @param direction Direction that shrinking
     *
     * @return Originally shrank amount
     */
    fun shrink(row:Row,value:Int,direction: Direction):Int{
        var shrink: Int

        if(direction == Direction.TOP){

            if(row.index==this.rows.size-1){
                return 0
            }

            val bottomRow = this.rows[row.index+1]

            if(row.height-value<=1){

                shrink = row.height -1

                row.height = 1

                bottomRow.height += shrink

            } else {

                bottomRow.height += value
                row.height -= value

                shrink = value
            }

            return shrink

        } else if( direction == Direction.BOTTOM) {

            if(row.index==0){
                return 0
            }

            val topRow = this.rows[row].index-1]

            if(topRow.height-value<=1) {
                shrink = topRow.height - 1

                topRow.height = 1
                row.height += shrink

            } else {

                topRow.height -= value
                row.height += value

                shrink = value
            }

            return shrink

        }

        return 0
    }

    /**
     * Returning a row by its index
     *
     * @param index What row to return
     *
     * @return Returning row if found a row for the given index. Otherwise null
     */
    fun getRow(index:Int):Row?{
        if(index<0)
            return null

        if(index>this.rows.size-1)
            return null

        return this.rows[index]
    }
}
