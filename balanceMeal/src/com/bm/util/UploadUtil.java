package com.bm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

public class UploadUtil {
	
	/* 加上 static 可以由类名直接调用 */
	public static Map<String, Object> upload(HttpServletRequest request, String dir){
		
		// 14. 创建map 来存储所有的信息返回给方法调用者
		Map<String, Object> map = new HashMap<String, Object>();
		/**
		 * 接收  ‘流形式’ 传递过来的数据
		 * */
		// 1. 判断是否为上传
		boolean flg = ServletFileUpload.isMultipartContent(request);
		System.out.println(flg);
		if(flg){
			// 2. 建立路径
			String path = request.getServletContext().getRealPath("/resource/"+dir);
			System.out.println(path);
			File file = new File(path); 
			// 3. 判断路径是否存在
			if(!file.isDirectory()){
				file.mkdirs();
				
			}
			/* ---- 4,5步的作用是：帮助解析请求的数据  ---- */
			// 4. 创建磁盘工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 5. 创建ServletFileUpload
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			try {
				// 6. 解析请求
				List<FileItem> fileItems = upload.parseRequest(request);
				Iterator<FileItem> i = fileItems.iterator();
				InputStream is = null;
				while (i.hasNext()) {
					FileItem fileItem = i.next();
					// 7.  获取网页中标签的name
					String filedName = fileItem.getFieldName();
					// 8. 获取流
					try {
						is = fileItem.getInputStream();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 9. 验证是否为普通控件（控件的值分为两种，一种是文本，一种是文件）
					if(fileItem.isFormField()){// 是普通控件
						// 10. 获取普通控件传递的值
						String value;
						try {
							value = Streams.asString(is,"UTF-8");
							map.put(filedName, value);
							System.out.println("普通空间:"+filedName+"值："+value);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}else{//文件输出
						String oldName = fileItem.getName();
						System.out.println("-----"+oldName+"-------");
						if(oldName!=null&&oldName.trim().length()>0){
							// 11. 获取名字后缀
							String ext = FilenameUtils.getExtension(oldName);
							// 12. 创建新的文件名
							String newName = UUID.randomUUID().toString()+"."+ext;
							// 13. 构建输出通道
							String newPath = path +"/"+newName;
							System.out.println("-----"+oldName+"-------");
							File outFile = new File(newPath);
							FileOutputStream os = null;
							try {
								os = new FileOutputStream(outFile);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							byte[] b = new byte[1024];
							int len = -1;
							try {
								while((len = is.read(b)) != -1){
									os.write(b, 0, len);
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								is.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								os.flush();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								os.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							map.put(filedName, dir+"/"+newName);
						}
					}
					
				}
				
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}// 不是上传就不处理
		return map;
	}
}
