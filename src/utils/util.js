
export const xhrErrHandler = (err,router,msgbox) => {
    console.log(err);
    if(err.response){
        let status = err.response.status;
        if(status === 401){
            msgbox('您还没有登录','权限错误')
            setTimeout(() => {
                router.push('/login')
            }, 500);
            
        }else if(status === 404){
            
        }else if(status === 500 || status === 501){
            console.log('网络异常，稍后重试！')
        }
    }
}