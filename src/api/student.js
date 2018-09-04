import http from 'axios'

const proxyURL = '/p'
const baseURL = proxyURL+'/p'

/********************************************************************************************* 
 * 学生信息
*********************************************************************************************/
/**
 * @function 获取学生信息
 */
export const getStudents = (url,params) => {
    return http(`${baseURL}/${url}`,{params})
}
/**
 * @function 新增学生信息
 */
export const addStudents = (params) => {
    return http(`${baseURL}/stu!newRec.action`,{params})
}
/**
 * @function 编辑学生信息
 */
export const editStudents = (params) => {
    return http(`${baseURL}/stu!edit.action`,{params})
}
/**
 * @function 删除学生信息
 */
export const delStudents = (params) => {
    return http(`${baseURL}/stu!del.action`,{params})
}
/**
 * @function 修改学生密码
 */
export const changePassword = (params) => {
    return http(`${baseURL}/stu!changePsw.action`,{params})
}
/**
 * @function 充值学生密码
 */
export const resetPassword = (params) => {
    return http(`${baseURL}/stu!resetPsw.action`,{params})
}
/**
 * @function 下载数据模板(学生信息)
 */
export const downloadStuTemplate = (params) => {
    return http(`${baseURL}/stu!downExcelModel.action`,{params})
}
/**
 * @function 导入学生信息
 */
export const importStudents = (params) => {
    return http(`${baseURL}/stu!importExcel.action`,{params})
}
/*************************************************************************************************
 * 班级信息
 ************************************************************************************************/
/**
 * @function 查询班级信息
 */
export const getClasses = (params) => {
    return http(`${baseURL}/cla!query.action`,{params});
}
/**
 * @function 新增班级信息
 */
export const addClass = (params) => {
    return http(`${baseURL}/cla!newRec.action`,{params});
}
/**
 * @function 编辑班级信息(包含修改状态以便于删除)
 */
export const editClass = (params) => {
    return http(`${baseURL}/cla!edit.action`,{params});
}
/**
 * @function 删除班级信息
 */
export const delClasses = (params) => {
    return http(`${baseURL}/cla!del.action`,{params});
}
/*************************************************************************************************
 * 班级学生
 ************************************************************************************************/
/**
 * @function 查询班级学生
 */
export const getClassStudents = (url,params) => {
    return http(`${baseURL}/${url}`,{params});
}
/**
 * @function 新增班级学生
 */
export const addClassStudent = (params) => {
    return http(`${baseURL}/claStu!newRec.action`,{params});
}
/**
 * @function 编辑班级学生
 */
export const editClassStudent = (params) => {
    return http(`${baseURL}/claStu!edit.action`,{params});
}
/**
 * @function 删除班级信息
 */
export const delClassStudents = (params) => {
    return http(`${baseURL}/claStu!del.action`,{params});
}