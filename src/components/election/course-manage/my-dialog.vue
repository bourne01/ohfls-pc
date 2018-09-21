<template>
    <el-dialog
        :visible.sync="isShow"
        width="726px"
        :modal-append-to-body="false"    
        @close="onClose"        
        class="my-dialog">
        <header slot="title">
            <span>添加{{title}}</span>
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
                <li class="right-header"><span>已选：</span><span><span class="selected-count">{{selectedList.length}}</span>个{{title}}</span></li>
                <li v-for="(item,idx) in selectedList" :key="idx">
                    <span>{{item.name}}</span>
                    <span>
                        <el-button 
                            icon="el-icon-close" 
                            circle
                            size="mini"
                            @click="onDeleteClick(idx)"
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
import { getTeachers } from '../../../api/teacher.js'
import { xhrErrHandler } from '../../../utils/util.js';
export default {
    props:['is-pop','type','current-list'],
    data(){
        return{
            title:'',
            dataList:[],//学期或班级或年级列表
            selectedList:[],//已选对象列表
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
                        this.title = '学期';
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
                        this.title = '年级';
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
                        this.title = '班级';
                        this.getClassListByGrade()
                        break;
                    case 4:
                        this.title = '教师';
                        this.getTeacherList()
                        break;
                }
                //console.log(typeof this.currentList);
                if(this.currentList)
                    this.selectedList = JSON.parse(this.currentList);//当前已经存在的对象列表 
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

        /**@function 获取教师信息 */
        getTeacherList(){
            getTeachers('thr!query.action',{})
                .then(res => {
                    this.dataList = [];
                    if(res.data.success){
                        for(let item of res.data.dataList){
                            this.dataList.push({name:item.thrName,id:item.thrId})
                        }
                    }else{
                        this.$message({
                            type:'error',
                            message:res.data.message
                        })
                    }
                })
                .catch(err => {
                    xhrErrHandler(err,this.$router,this.$message)
                })
        },

        /**
         * @function 监听添加对象事件
         * @param {对象数据，来自Element提供}
         */
        onAddClick(data){
            let selectedListToString = JSON.stringify(this.selectedList);//已被选择的列表字JSON字符串化
            //弹窗类型，type的值：1添加学期、2添加年级、3添加班级，-1默认
            if(this.type === 3 && typeof data.children === 'object'){//添加班级                
                for(let item of data.children){
                    if(!selectedListToString.includes(JSON.stringify(item))){//已被选择的列表"selectedList"还没有的，则添加该班级
                        this.selectedList.push(item)
                    }
                }
            }else{//添加学期或年级
                if(!selectedListToString.includes(JSON.stringify(data)))//已被选择的列表"selectedList"还没有的，则添加该学期或班级
                    this.selectedList.push(data);
            }
        },

        /**
         * @function 监听从已添加列表中删除对象事件 
         * @param {对象在数组中的下标} index
        */
       onDeleteClick(index){
           this.selectedList.splice(index,1) 
       },
        /**
         * 监听对话框关闭事件，然后向父组件传递关闭事件
         */
        onClose(){
            this.$emit('dialog-close')
        },
        
        /**
         * @function 监听点击确认对话框按钮事件
         */
        onConfirmBtn(){
            console.log(this.selectedList);
            this.$emit('selected',JSON.stringify(this.selectedList),this.type);
            this.onClose();
        },

        /**@function 过滤文本 */
        filterNode(value, data) {
            if (!value) return true;
            return data.name.indexOf(value) !== -1;
        }
    },

    watch: {
        /**@function 监听输入内容的变化 */
        filterText(val) {
            this.$refs.tree2.filter(val);
        }
    },
    
    mounted(){       
        //this.selectedList = JSON.parse(this.currentList);//当前已经存在的对象列表 
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
    .selected-count{
        font-size:20px;
        color:#fa6b5a;
        margin-right:5px;
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


