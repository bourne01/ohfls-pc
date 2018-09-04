<template>
  <div class="submit-button" :class="{'active':isClicked}" @click="onClick">
      登录    
  </div>
</template>

<script>
export default {
    data(){
        return{
            isClicked:false,
        }
    },
    methods:{
        onClick(){
            this.isClicked = true;
            let user = this.$store.state.user.user;
            this.$store.dispatch('user/login',user)
                .then(res => {
                    console.log(res);
                  if(res.success){
                        this.$router.push('/home');
                    }
                })
                .catch(err => {
fqxxs
                })        
        }
    },
    created(){

    }
}
</script>

<style scoped>
    
    .submit-button{
        border-radius: 5px;
        height: 58px;
        line-height: 58px;
        background-color:#0d714a;
        font-size:24px;
        width:380px;
        text-align: center;
        color:#fff;          
        cursor: pointer;
    }
    .active{
        border: 1px solid #deb163;
        height: 54px;
        line-height: 54px;
        width:380px;
    }     
</style>


