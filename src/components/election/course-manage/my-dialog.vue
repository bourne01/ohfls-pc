<template>
    <el-dialog
        :visible.sync="isShow"
        width="726px"
        :modal-append-to-body="false"    
        @close="onClose"        
        class="my-dialog">
        <header slot="title">
            <span>{{title}}</span>
        </header>
        <section>
            <div class="left">
                <el-input
                    placeholder="输入关键字进行过滤"
                    v-model="filterText">
                </el-input>

                <el-tree
                    class="filter-tree"
                    :data="dataList"
                    :props="defaultProps"
                    :filter-node-method="filterNode"
                    ref="tree2">
                    <span class="custom-tree-node" slot-scope="{ node, data }">
                        <span>{{ node.label }}</span>
                        <span>                            
                            <el-button 
                                icon="el-icon-plus" 
                                circle
                                size="mini"
                                @click="onAddClick(data)"
                                ></el-button>          
                        </span>
                    </span>
                </el-tree>
            </div>            
            <ul class="right">
                <li class="right-header"><span>已选：</span><span>{{22}}个{{title}}</span></li>
                <li v-for="(item,idx) in selecedList" :key="idx">
                    <span>{{item.name}}</span>
                    <span>
                        <el-button 
                            icon="el-icon-close" 
                            circle
                            size="mini"
                            @click="onAddClick(data)"
                            ></el-button>
                        </span>
                </li>
            </ul>
        </section>
        <footer>
            <button @click="onConfirmBtn">确定{{title}}</button>
        </footer>
    </el-dialog>
</template>

<script>
import { getSelector } from '../../../api/public.js'
export default {
    props:['is-pop','type'],
    data(){
        return{
            title:'',
            dataList:[],//学期或班级或年级列表
            selecedList:[],//已选对象列表
            multipleSelection: [],
            filterText: '',
            defaultProps: {
                children: 'children',
                label: 'name'
            }
        }
    },
    computed:{
        isShow:{
            /**@function 根据来自父组件属性值，打开哪个标题名称的弹窗 */
            get:function(){
                let params = {};
                switch(this.type){
                    case 1:
                        this.title = '添加学期';
                        params = {
                            f:'uxTerm',
                            /* state:2, */
                            simple:0
                        };
                        getSelector(params)
                            .then(res => {
                                this.dataList = res.data.dataList;
                            })
                            .catch(err => {

                            })
                        break;
                    case 2:
                        this.title = '添加年级';
                        params = {
                            f:'uxCode',
                            /* state:2, */
                            codeType:35,
                            simple:0
                        };
                        getSelector(params)
                            .then(res => {
                                this.dataList = res.data.dataList;
                            })
                            .catch(err => {

                            })
                        break;
                    case 3:
                        this.title = '添加班级';
                        this.getClassListByGrade()
                        break;
                }
                
                return this.isPop;
            },
            set:function(val){
            }
        }
    },
    methods:{
        /**
         * @function 根据年级，获取该年级下所有的班级 
         */
        getClassListByGrade(){
            let params = {
                    f:'uxCode',
                    /* state:2, */
                    codeType:35,
                    simple:0
            };
            getSelector(params)
                .then(res => {
                    this.dataList = res.data.dataList;
                    for(let grade of this.dataList){//根据年级列表，获取每个年级下的班级列表
                        let _params = {
                            f:'uxCla',
                            simple:0,
                            grades:grade.id
                        };
                        getSelector(_params)
                            .then(res => {
                                let classList = res.data.dataList;
                                this.$set(grade,'children',classList);//添加新属性，让vue更新页面
                            })
                            .catch(err => {

                            })
                    }
                })
                .catch(err => {

                })
            
        },
        /**
         * @function 监听添加对象事件
         * @param {对象数据，来自Element提供}
         */
        onAddClick(data){
            console.log(data);
            if(this.type === 3 && typeof data.children === 'object'){//弹窗类型，type的值：1添加学期、2添加年级、3添加班级，-1默认
                console.log(data.children);
                this.selecedList = this.uniqueArr(this.selecedList.concat(data.children));
            }else{
                this.selecedList.push(data);
            }
            console.log(this.selecedList);
        },

        /**
         * 监听对话框关闭事件，然后向父组件传递关闭事件
         */
        onClose(){
            this.$emit('dialog-close')
        },
        /**@function 数组去重 */
        uniqueArr(arr) {
            var n = {},
                r = [],
                len = arr.length,
                val, type;
            for (var i = 0; i < len; i++) {
                val = arr[i];
                type = typeof val;
                if (!n[val]) {
                    n[val] = [type];
                    r.push(val);
                } else if (n[val].indexOf(type) < 0) {
                    n[val].push(type);
                    r.push(val);
                }
            };
            return r;
        },
        /**
         * @function 监听点击确认对话框按钮事件
         */
        onConfirmBtn(){
            this.onClose();
        },
        handleSelectionChange(){},
        filterNode(){},
    },
    mounted(){
        
    }
}
</script>

<style>
    .my-dialog .el-dialog__close{
        font-size:20px;
        color:#fff;
    }
    .my-dialog .el-dialog__header{
        padding-bottom:14px;
        border-bottom:1px solid #f1f1f1;
        text-align: left;
        background-color:#0d714a;
    }
    .my-dialog .el-dialog__body{
        padding:0;
    }    
    .my-dialog .el-tree-node__content{
        height:32px;
        line-height:32px;
    }
    .my-dialog .el-button--mini.is-circle{
        padding:2px;
    }
</style>

<style scoped>
    .my-dialog{
        font-size:18px;        
    }
    header>span{
        color:#fff;
    }
    .left{
        width:289px;
        padding-top:20px;
        padding-right:20px;
        border-right:1px solid #f1f1f1;
    }
    .left .el-input{
        margin-bottom:20px;
    }
    .right{
        padding:20px 0 0 20px;
        width:376px;
    }
    .right-header{
        height:40px;
        line-height:40px;
        display:flex;
        justify-content:space-between;
    }
    .right li:hover{
        background-color:#f6f9fb;
    }
    .right .el-icon-circle-close{
        font-size:18px;
        color:#bbbbc6;
    }
    .right .el-icon-circle-close:hover{
        color:#ff7a7b;
    }
    section{
        padding:0 20px;
        display:flex;
    }
    .custom-tree-node{
        display:flex;
        justify-content:space-between;
        width: 100%;
        padding-right:20px;
    }
    .el-tree,
    .right ul{
        height:400px;
        overflow:auto;
    }
    .right li{
        padding:10px;
        display:flex;
        justify-content: space-between;
    }
    footer{
        height: 65px;
        line-height: 65px;
        display: flex;
        justify-content: flex-end;
        padding:0 20px;
        border-top:1px solid #f1f1f1;
        background-color:#eff2f4;
    }
    footer button{
        height:32px;
        line-height:32px;
        width:126px;
        font-size:14px;
        background-color:#2185ff;
        outline:none;
        border:none;
        color:#fff;
        align-self: center;
        border-radius:3px;
    }
</style>


