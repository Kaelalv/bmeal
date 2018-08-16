package com.bm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringEscapeUtils;

import com.bm.model.News;
import com.bm.model.PageBean;
import com.bm.model.Value;
import com.bm.util.DBUtil;

public class ValueService {
	/* --- 第一步 ：每日一餐的 添加 --- */
	// 2. 验证添加的标题是否存在
	public int checkExit(String foodname){
		String sql = "select count(*) as num from value where foodname = ?";
		//DBUtil.query(sql,mealtit)这里查询出来的结果是map类型，所有通过查询结果来获取“num”的值
		return Integer.parseInt(DBUtil.query(sql,foodname).get("num").toString());
	}
	// 1.写添加每日一餐的方法	
	public void valueAdd(Map<String,Object> map){
		
		// 1.1 写完插入数据后，发现需要检查插入的数据是否已存在，所以要写第2步的内容，
		// 因此下面调用checkExit()这个方法
		int num = checkExit(map.get("foodname").toString());
		if(num==0){// 说明当前没有该账号，可以添加
			String sql = "insert into value (foodname,foodimg,foodvalue,valuepublish)values(?,?,?,?)";
			DBUtil.executeUpdate(sql, new Object[]{
					map.get("foodname"),
					map.get("foodimg"),
					map.get("foodvalue"),
					map.get("valuepublish")
			});
		}else{
			throw new RuntimeException("该标题已存在");
		}
		
	}
	
	/* ------ 添加的内容到此结束 ------- */
	
	
	/*---- 4. 分页查询用户列表 ----------- */
	public PageBean getValueList(Value value,PageBean pageBean){
		// 1. 获取通过当前方法所需要得到的信息范围（获取分页信息）
		int pageNow = pageBean.getPageNow();
		int pageSize = pageBean.getPageSize();
		// 2. 拼接查询语句  -- 第一部分
		StringBuilder sb = new StringBuilder("select * from value where 1=1 ");
				
		if(value.getFoodname()!=null && value.getFoodname().trim().length()>0){
			sb.append(" and foodname like '%"+ StringEscapeUtils.escapeSql(value.getFoodname()) +"%'");
		}
		
		if(value.getValuepublish()!=null){
			System.out.println("111111111111");
			sb.append(" and valuepublish = '"+ value.getValuepublish() +"'");
		}
		
		// 2. 拼接分页条件  -- 第三部分
		String sql = sb.toString()+" limit " + pageSize * (pageNow-1) +"," + pageSize;
		// 3. 执行查询语句
		List<Map<String,Object>> list = DBUtil.list(sql);
		// 4. 验证查询是否有结果
		if(list != null && list.size()>0){
			// 5. 转换（反射）准备：创建一个空间的list存放everydaymeal对象
			List<Value> valueList = new ArrayList<Value>();
			// 6. 通过循环将原查询结果list拆分成map
			for(Map<String,Object> map : list){
				// 7. 创建User对象存储map转换的结果
				Value value1 = new Value();
				try {
					BeanUtils.populate(value1, map);
				} catch (Exception e) {
					System.out.println("反射出错，错误原因可能是实体bean属性与字段名出现偏差");
					e.printStackTrace();
				}
				// 8. 转换一个对象成功， 存入everydayList里面
				valueList.add(value1);
			}
			// 9. 完整的查询结果转换完成
			pageBean.setData(valueList);			
		}
		// 10. 获取相应的数据条数
		String countSql = "select count(*) as num from ("+sb.toString()+")t";
		//Map<String, Object> map = DBUtil.query(countSql);
		int totalCount = Integer.parseInt(DBUtil.query(countSql).get("num").toString());
		pageBean.setTotalCount(totalCount);
		int totalPage = totalCount%pageSize==0 ? totalCount/pageSize : totalCount/pageSize+1;
		pageBean.setTotalPage(totalPage);
		return pageBean;
		
		/*---- 4. 分页查询用户列表 结束，跳回Servlet： 12. 调用service----------- */
	}
	
	/* --------------- 编辑每日一餐的信息 -------------- */
	// 1. 根据id获取一条员工信息
	public Value getValueById(Integer id){
		System.out.print(id);
		String sql = "select * from value where value_id = ?";
		Map<String,Object> map = DBUtil.query(sql,id);
		System.out.print(map);
		//System.out.println(map);
		Value value = new Value();
		try {
			BeanUtils.populate(value, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	// 2. 更新信息
	public void updateValueById(Value value){
		String sql = "update value set foodname = ? , foodvalue = ?  where value_id = ?";
		int num = DBUtil.executeUpdate(sql,new Object[]{
				
				value.getFoodname(),
				value.getFoodvalue(),
				//value.getValuepublish(),
				value.getValue_id()
				//everydayMeal.getMealimg()
		});
		if(num <0){
			throw new RuntimeException("更新失败");
		}
	}
	
	
	/**
	 *  3. 删除某条数据 -- 开始
	 * */
	
	public void deleteValueById(Integer id){
		String sql = "delete from value where value_id = ?";
		
		DBUtil.executeUpdate(sql, id);
		
	}
}
