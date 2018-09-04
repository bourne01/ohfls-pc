<template>
    <div class="building-code">
        <select class="SearchStyle1">
           <option value="building-code" selected = "selected">教学楼编号</option> 
           <option value=""></option> 
            <option value=""></option> 
             <option value=""></option> 
        </select>
    </div>
</template>
<script>
export default {
    date(){
        return{
            options:[{
                value:'选项1',
                label:'王帅帅'
            },
            {
                value:'选项2',
                label:'王帅帅'
            },]
        }
    }
}
</script>
<style scoped>
.SearchStyle1{
    background-color:rgb(246,249,251 );
    border: 1px solid #e5e9ec;
    border-radius: 4px;
    height: 30px;
    width: 10%;
    float: left;
    margin-top: 1.2%;
    margin-left: 5%;
    padding-left: 0.5%;
    cursor: pointer;
    color: gray;
    font-family: 'Microsoft YaHei';
    font-size: 16px;
}
</style>

