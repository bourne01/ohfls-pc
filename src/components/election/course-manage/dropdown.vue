<template>
    <div>
        <div class="avatar"></div>           
            <el-dropdown @command="onCmd" trigger="hover" placement="bottom-start" class="item-list" >    <!--选择授课教师的下拉菜单--> 
                <span class="el-dropdown-link">
                    {{curItem}}<i v-if="totalItem>1" class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown" >
                    <el-dropdown-item 
                        v-for="(item,idx) in itemList" 
                        :key="idx"
                        :command="item" 
                        style=" height:40px;margin-bottom:15px;padding-top:5px;" >
                                <span class="avatar-list"></span>
                                <span>{{item}}</span>
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown> 
     </div>  
</template>

<script>
export default {
    props:['items'],
    data(){
        return{
            curItem:'',//当前正在展示的教师名称
            totalItem:1,//课程授课教师人数，默认一位            
        }
    },
    computed:{
        /**
         * @function 对来自父组件的字符串数据，按照','切分为数据组
         * 同时给当前显示教师的赋值，且给课程授课教师数赋值
         * @return 教师名称的数组
         */
        itemList:function(){
            if(!this.items){
                this.curItem = '未指定';
                return
            }
            let _items = this.items.split(',');
            this.curItem = _items[0];
            this.totalItem = _items.length;
            return _items
        }
    },
    methods: {
        /**@function 对下拉列表项进行处理，切换当前显示的教师
         * @param {来自下拉列表项中属性comman} command
        */
        onCmd(command) {
            this.curItem = command
        }
    }
}
</script>

<style scoped>
    .avatar{                                    /*教师头像样式*/
        /* background-color: black;
        height: 45px;
        width: 45px;
        border-radius: 50%;
        margin: 0 -15% 0 15%;
        float: left; */
    }
    .item-name{                              /*教师名字位置*/
         margin:  9px 0 0 20%;
        float: left;
    }
    .avatar-list{                         /*头像列表样式*/
        /* height: 45px;
        width: 45px;
        background-color: #5b9bfd;
        float: left;
        border-radius: 50%;
        margin-right: 10px;
        margin-top:-5px;  */
    }
    .el-dropdown-menu {                         /*下拉菜单统一宽度*/
        width: 160px;
    }
    .el-icon-arrow-down.el-icon--right{       /*下拉菜单的颜色*/
        color:blue;
        font-weight: bold;
    }
    .el-dropdown{
        margin-top: 9px;
        display: inline-block;
    }
</style>


