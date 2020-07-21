package com.sunnyweather.android.bean

data class TreeResponse(val data: List<Tree>)

data class Tree(val children: List<TreeChildren>, val name: String)

data class TreeChildren(val id: Int, val name: String)
