package com.donbala.util;

import com.donbala.loginManagement.model.CmsMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @CLassName: TreeNodeUtil
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/4-13:51
 * @Description: todo
 **/
public class TreeNodeUtil {

    /**
    *@methodname:
    *@description: 将菜单转为一个树
    *@param:
    *@return:
    *@date: 2019/7/4 13:56
    *@author: wangran
    */
    public   List<TreeNode>  getNodeTree(List<CmsMenu> list) {

            List<TreeNode> menuTreeNodeList = new ArrayList<>();

            for (CmsMenu menu : list) {
                TreeNode treeNode = new TreeNode();
                List<TreeNode> nodes = new ArrayList<>();
                if (menu.getParentmenuid().equals("0000")) {
                    treeNode.setNodeid(menu.getMenuid());
                    treeNode.setParentid(menu.getParentmenuid());
                    treeNode.setText(menu.getMenuname());
                    nodes = getChildren(menu.getMenuid(),list);
                    if(!nodes.isEmpty())
                    {
                        treeNode.setNodes(nodes);
                    }
                    menuTreeNodeList.add(treeNode);
                }
            }

        return  menuTreeNodeList;
    }

    private List<TreeNode> getChildren(String menuid,List<CmsMenu> list) {

        List<TreeNode> nodes = new ArrayList<>();
        for (CmsMenu menu : list) {
            TreeNode treeNode = new TreeNode();
            if (menu.getParentmenuid().equals(menuid)) {
                treeNode.setNodeid(menu.getMenuid());
                treeNode.setParentid(menu.getParentmenuid());
                treeNode.setText(menu.getMenuname());
                nodes.add(treeNode);
            }
        }

        return  nodes;
    }


    List<TreeNode> nodes = new ArrayList<TreeNode>();

//    public TreeNodeUtil(List<TreeNode> nodes) {
//        super();
//        this.nodes = nodes;
//    }

    /**
     * 构建树形结构
     *
     * @return
     */
    public List<TreeNode> buildTree() {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        List<TreeNode> rootNodes = getRootNodes();
        for (TreeNode rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**
     * 递归子节点
     *
     * @param node
     */
    public void buildChildNodes(TreeNode node) {
        List<TreeNode> children = getChildNodes(node);
        if (!children.isEmpty()) {
            for (TreeNode child : children) {
                buildChildNodes(child);
            }
            node.setNodes(children);
        }
    }

    /**
     * 获取父节点下所有的子节点
     *
     * @param pnode
     * @return
     */
    public List<TreeNode> getChildNodes(TreeNode pnode) {
        List<TreeNode> childNodes = new ArrayList<TreeNode>();
        for (TreeNode n : nodes) {
            if (pnode.getNodeid().equals(n.getParentid())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    /**
     * 判断是否为根节点
     *
     * @return
     */
    public boolean rootNode(TreeNode node) {
        boolean isRootNode = true;
        for (TreeNode n : nodes) {
            if (node.getParentid().equals(n.getNodeid())) {
                isRootNode = false;
                break;
            }
        }
        return isRootNode;
    }

    /**
     * 获取集合中所有的根节点
     *
     * @return
     */
    public List<TreeNode> getRootNodes() {
        List<TreeNode> rootNodes = new ArrayList<TreeNode>();
        for (TreeNode n : nodes) {
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }

}
