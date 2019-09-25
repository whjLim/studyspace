package com.whjlim.springioc.di;

/**
 * Created by BlackWatch on 2019/9/24 23:45
 */
public class BookwormOracle implements Oracle{

    private Encyclopedia encyclopedia;

    public void setEncyclopedia(Encyclopedia encyclopedia) {
        this.encyclopedia = encyclopedia;
    }

    @Override
    public String defineMeaningOfLife() {
        return "百科全书是浪费钱的，走出去，看看";
    }
}
