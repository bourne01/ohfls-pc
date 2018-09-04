import http from 'axios'

const proxyURL = '/p'
const baseURL = proxyURL+'/p'
/**************************************************************************************************
 * 教师信息
 *************************************************************************************************/
/**
 * @function 查询教师信息
 */
export const getTeachers = (url,params) => {
    return http(`${baseURL}/${url}`,{params});
}
/**
 * @function 新增教师信息
 */
export const addTeacher = (params) => {
    return http(`${baseURL}/thr!newRec.action`,{params});
}
/**
 * @function 编辑教师信息(包含修改状态以便于删除)
 */
export const editTeacher = (params) => {
    return http(`${baseURL}/thr!edit.action`,{params});
}
/**
 * @function 删除教师信息
 */
export const delTeachers = (params) => {
    return http(`${baseURL}/thr!del.action`,{params});
}
/**
 * @function 下载数据模板(教师信息)
 */
export const downloadTeacherTemplate = (params) => {
    return http(`${baseURL}/thr!downExcelModel.action`,{params});
}
/**
 * @function 导入教师信息
 */
export const importTeachers = (params) => {
    return http(`${baseURL}/thr!importExcel.action`,{params});
}
/**************************************************************************************************
 * 教师权限
 *************************************************************************************************/
/**
 * @function 查询教师权限
 */
export const getThrPermissions = (url,params) => {
    return http(`${baseURL}/${url}`,{params});
}
/**
 * @function 新增教师权限
 */
export const addThrPermission = (params) => {
    return http(`${baseURL}/thrPow!newRec.action`,{params});
}
/**
 * @function 编辑教师权限
 */
export const editThrPermission = (params) => {
    return http(`${baseURL}/thrPow!edit.action`,{params});
}
/**
 * @function 删除教师权限
 */
export const delThrPermissions = (params) => {
    return http(`${baseURL}/thrPow!del.action`,{params});
}