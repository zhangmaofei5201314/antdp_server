package com.donbala.util;

import java.util.List;

/**
 * @CLassName: MenuTreeNode
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/4-13:43
 * @Description: todo
 **/
public class TreeNode {

    private  String text;

    private  String nodeid;

    private String parentid;

    private List<TreeNode> nodes;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public List<TreeNode>  getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode>  nodes) {
        this.nodes = nodes;
    }
    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

}
