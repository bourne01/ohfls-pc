/** 学生选课状态 */

	/** 选课计划Id */ private Integer xkpId   ;
	/** 学期Id     */ private Integer termId  ;
	/** 计划编号   */ private String  planNO  ;
	/** 计划名称   */ private String  planName;
	/** 学期名称   */ private String  termName;
	/** 学生Id     */ private Integer stuId   ;
	/** 学籍号     */ private String  stuNO   ;
	/** 学生姓名   */ private String  stuName ;
	/** 学生性别   */ private String  stuSex  ; //男、女
	/** 班级名称   */ private String  claName ;
	/** 班级年级   */ private Integer claGrade; //就是开班年份，系统代码(35)，默认-1代表没有指定年级

	/** 学生选课Id */ private Integer stuCouId; // *
	/** 待选课Id   */ private Integer selCouId; // *
	/** 选课状态   */ private Byte    state   ; // * 1预选 2选中(默认) 3确认
	/** 课程类型   */ private Integer couType ; // * 系统代码11 2普通(默认) 4自修研习
	/** 父课程Id   */ private Integer parCouId; // * 组合课程时有
	/** 课程编号   */ private String  couNO   ; // *
	/** 课程名称   */ private String  couName ; // *
	
	/** 排序字段 */ private String[] orderFd; //排序字段的名称，倒序前面加“@”，可以多个
	
	//==================================================================================================================
	// 查询
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：查询
	 * 使用者：
	 *
	 * URL：p/stuXkState!query.action
	 *
	 * 参数
	 * start        可选   记录开始位置
	 * limit        可选   获取记录数量
	 * xkpId        必需   选课计划Id
	 * termId       可选   学期Id
	 * planNO       可选   计划编号
	 * planName     可选   计划名称
	 * termName     可选   学期名称
	 * stuId        可选   学生Id
	 * stuNO        可选   学籍号
	 * stuName      可选   学生姓名
	 * stuSex       可选   学生性别 男、女
	 * claName      可选   班级名称
	 * claGrade     可选   班级年级 就是开班年份，系统代码(35)，默认-1代表没有指定年级
	 * orderFd      可选   排序字段的字段名称，倒序前面加“@”
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[R_StuXkState1]}
	 *
	 */

	//==================================================================================================================
	// 导出学生选课状态
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：导出学生选课状态
	 * 使用者：
	 *
	 * URL：p/stuXkState!export.action
	 *
	 * 参数：参考query
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：Excel文件
	 *
	 */
	
	//==================================================================================================================
