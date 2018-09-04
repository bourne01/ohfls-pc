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
            
        </section>
        <footer>
            <button>确定{{title}}</button>
        </footer>
    </el-dialog>
</template>

<script>
export default {
    props:['is-pop','type'],
    data(){
        return{
            title:'',
             
        multipleSelection: [],
         filterText: '',
        data2: [{
          id: 1,
          label: '一级 1',
          children: [{
            id: 4,
            label: '二级 1-1',
            children: [{
              id: 9,
              label: '三级 1-1-1'
            }, {
              id: 10,
              label: '三级 1-1-2'
            }]
          }]
        }, {
          id: 2,
          label: '一级 2',
          children: [{
            id: 5,
            label: '二级 2-1'
          }, {
            id: 6,
            label: '二级 2-2'
          }]
        }, {
          id: 3,
          label: '一级 3',
          children: [{
            id: 7,
            label: '二级 3-1'
          }, {
            id: 8,
            label: '二级 3-2'
          }]
        }, {
          id: 2,
          label: '一级 2',
          children: [{
            id: 5,
            label: '二级 2-1'
          }, {
            id: 6,
            label: '二级 2-2'
          }]
        }, {
          id: 2,
          label: '一级 2',
          children: [{
            id: 5,
            label: '二级 2-1'
          }, {
            id: 6,
            label: '二级 2-2'
          }]
        }, {
          id: 2,
          label: '一级 2',
          children: [{
            id: 5,
            label: '二级 2-1'
          }, {
            id: 6,
            label: '二级 2-2'
          }]
        }, {
          id: 2,
          label: '一级 2',
          children: [{
            id: 5,
            label: '二级 2-1'
          }, {
            id: 6,
            label: '二级 2-2'
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
        }
    },
    computed:{
        isShow:{
            /**@function 根据来自父组件属性值，打开哪个标题名称的弹窗 */
            get:function(){
                switch(this.type){
                    case 1:
                        this.title = '添加学期';
                        break;
                    case 2:
                        this.title = '添加年级';
                        break;
                    case 3:
                        this.title = '添加班级';
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
         * 监听对话框关闭事件，然后向父组件传递关闭事件
         */
        onClose(){
            this.$emit('dialog-close')
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


