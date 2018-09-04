<template>
    <div>
        <span 
            class="task-state"
            :style="{color:taskState===2?'#1cd06c':''}">{{taskStateTxt}}</span>
    </div>
</template>

<script>
export default {
    props:['task-state'],
    data(){
        return{
        }
    },
    computed:{
        taskStateTxt:function(){
            let stateTxtList = ['','未开启','开启','关闭','完成'];//选课任务状态
            if(!this.taskState)return '未知';
            return stateTxtList[this.taskState];
        }
    },
    mounted(){
    }
}
</script>

<style scoped>

</style>


