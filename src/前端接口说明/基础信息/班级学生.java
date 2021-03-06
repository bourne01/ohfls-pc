/** 班级学生 */


	/** 班级Id       */ private Integer claId    ;
	/** 学生Id       */ private Integer stuId    ;
	/** 学生入班日期 */ private Date    joinDate ;
	/** 学生离班日期 */ private Date    leaveDate;
	/** 备注         */ private String  remark   ;
	/** 班级编号     */ private String  claNO    ;
	/** 班级名称     */ private String  claName  ;
	/** 班级年级     */ private Integer claGrade ; //就是开班年份，系统代码(35)
	/** 班主任Id     */ private Integer masterId ; //就是班主任的教师Id
	/** 班级记录状态 */ private Byte    claState ; //2正常(默认) 3关闭 4历史
	/** 学籍号       */ private String  stuNO    ;
	/** 学生姓名     */ private String  stuName  ;
	/** 学生性别     */ private String  stuSex   ; //男(默认)、女
	/** 学分         */ private Double  credit   ;
	/** 学生班级Id   */ private Integer stuClaId ; //-1代表未分配班级
	/** 学生年级     */ private Integer stuGrade ; //就是入学年份，系统代码35
	/** 2正常(默认)  */ private Byte    stuTo    ; //3休学 4退学 5毕业
	/** 学生记录状态 */ private Byte    stuState ; //2正常(默认) 3关闭 4历史

	/** 排序字段     */ private String[] orderFd; //排序字段的名称，倒序前面加“@”，可以多个
	/** 学生Id(多个) */ private String   stuIds ; //多个Id用“,”隔开

	//==================================================================================================================
	// 查询
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：查询
	 * 使用者：
	 *
	 * URL：p/claStu!query.action
	 *
	 * 参数
	 * start    可选   记录开始位置
	 * limit    可选   获取记录数量
	 * claId    可选   班级Id
	 * stuId    可选   学生Id
	 * remark   可选   备注
	 * inDate   可选   具体日期（查这个时间点的学生）
	 * orderFd  可选   排序字段的字段名称，倒序前面加“@”
	 * type     可选   1=不含离开学生
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[ClaStu]}
	 *
	 */

	//==================================================================================================================
	// 查询2 -- 增加班级、学生信息
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：查询2 -- 增加班级、学生信息
	 * 使用者：
	 *
	 * URL：p/claStu!query2.action
	 *
	 * 参数
	 * start     可选   记录开始位置
	 * limit     可选   获取记录数量
	 * claId     可选   班级Id
	 * stuId     可选   学生Id
	 * remark    可选   备注
	 * claNO     可选   班级编号
	 * claName   可选   班级名称
	 * claGrade  可选   班级年级 就是开班年份，系统代码(35)
	 * masterId  可选   班主任Id 就是班主任的教师Id
	 * claState  可选   班级记录状态 2正常(默认) 3关闭 4历史
	 * stuNO     可选   学籍号
	 * stuName   可选   学生姓名
	 * stuSex    可选   学生性别 男(默认)、女
	 * credit    可选   学分
	 * stuClaId  可选   学生班级Id -1代表未分配班级
	 * stuGrade  可选   学生年级 就是入学年份，系统代码35
	 * stuTo     可选   2正常(默认) 3休学 4退学 5毕业
	 * stuState  可选   学生记录状态 2正常(默认) 3关闭 4历史
	 * inDate    可选   具体日期（查这个时间点的学生）
	 * orderFd   可选   排序字段的字段名称，倒序前面加“@”
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[V_ClaStu]}
	 *
	 */

	//==================================================================================================================
	// 新增
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：新增
	 * 使用者：
	 *
	 * URL：p/claStu!newRec.action
	 *
	 * 参数
	 * claId      必需   班级Id
	 * stuId      必需   学生Id
	 * remark     可选   备注
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"data":{ClaStu}}
	 *
	 * 注意：这会同时变更学生的当前班级
	 *
	 */

	//==================================================================================================================
	// 修改
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：修改
	 * 使用者：
	 *
	 * URL：p/claStu!edit.action
	 *
	 * 参数
	 * claId      必需   班级Id
	 * stuId      必需   学生Id
	 * remark     可选   备注
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"message":"成功信息"}
	 *
	 */

	//==================================================================================================================
	// 删除
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：删除
	 * 使用者：
	 *
	 * URL：p/claStu!del.action
	 *
	 * 参数
	 * claId     必需   班级Id
	 * stuIds    必需   学生Id，多个用“,”隔开
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"message":"成功信息"}
	 *
	 */

	//==================================================================================================================
