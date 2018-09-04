<template>
    <div>
        <span @click="changeStatus">{{status}}</span>
    </div>
</template>

<script>
export default {
    props:['current-row'],
    computed:{
        status:{
            get:function(){
                let statusTxt = '';
                switch(this.currentRow.status){
                    case 2:
                        statusTxt = '正常';
                        break;
                    case 3:
                        statusTxt = '历史';
                        break;
                    case 4:
                        statusTxt = '关闭'
                }
                return statusTxt
            },
            set:function(val){
                this.currentRow.status = val;
            }
        }
    },
    methods:{
        /**@function 改变状态 2正常(默认)、3历史、4关闭*/
        changeStatus(){
            let params = null;
            if(this.currentRow.status === 2){
                params = {
                    autoId:this.currentRow.autoId,
                    status:4
                }
            }else if(this.currentRow.status === 4){
                params = {
                    autoId:this.currentRow.autoId,
                    status:2
                }
            }
            this.$store.dispatch('teacher/editTeacher',params)
        }
    }
}
</script>

<style scoped>
    span{
        cursor: pointer;
    }
</style>


