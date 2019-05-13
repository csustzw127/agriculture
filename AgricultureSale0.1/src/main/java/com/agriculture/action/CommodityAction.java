package com.agriculture.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.agriculture.service.CommodityService;
import com.agriculture.vo.Category;
import com.agriculture.vo.Commodity;
import com.agriculture.vo.Farmer;
import com.agriculture.vo.Page;
import com.opensymphony.xwork2.Action;

public class CommodityAction {
	
	private int currPage;
	private int cate_id;
	private int commodityId;
	private String name;
	private double price;
	private String info;
	private String specs;
	private File image;
	 //提交过来的file的名字
    private String imageFileName;
    private CommodityService commodityService;
    
    /*
     * 根据名称查询
     */
    public String findByName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String condition = "";
		try {
			condition = new String(name.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page<Commodity> page = commodityService.findByName(condition,currPage);
		
		page.setCurrPage(currPage);
		request.setAttribute("page", page);
		return "success";
	}
    /*
     * 根据商品id查询单个商品并显示详细信息
     */
	public String findByCommId() {
		Commodity comm = commodityService.findByCommId(commodityId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("commodity", comm);
		return "success";
	}
    /*
     * 根据类别查询
     */
    public String findByCategory() {
		Page<Commodity> page = commodityService.findByCategory(currPage,cate_id);
		HttpServletRequest request = ServletActionContext.getRequest();
		page.setCurrPage(currPage);
		request.setAttribute("page", page);
		return "success";
	}
    /*
     * 查询农户自己上传的产品
     */
    public String findByFarmer() {
    	Farmer farmer = (Farmer) ServletActionContext.getRequest().getSession().getAttribute("farmer");
    	Page<Commodity> page = commodityService.findByFarmer(farmer,currPage);
		HttpServletRequest request = ServletActionContext.getRequest();
		page.setCurrPage(currPage);
		page.setUrl("/AgriculureSale0.1/commodity/findByFarmer.action");
		
		JSONArray jsonArr = new JSONArray();
        JSONObject jsonArea = new JSONObject();
        JSONObject jsonComm;
        //填充json对线的普通属性
        jsonArea.put("currPage", page.getCurrPage());
        jsonArea.put("totalPage", page.getTotalPage());
        jsonArea.put("url", page.getUrl());
        //填充json对象的产品数组数据
        List<Commodity> list = page.getPageList();
        for(int i=0;i<list.size();i++) {
        	Commodity comm = list.get(i);
        	jsonComm = new JSONObject();
        	jsonComm.put("id", comm.getId());
        	jsonComm.put("name", comm.getName());
        	jsonComm.put("image", comm.getImage());
        	jsonComm.put("price", comm.getPrice());
        	jsonComm.put("specs", comm.getSpecs());
            jsonArr.add(jsonComm);
        }
        jsonArea.put("commList", jsonArr);
        printJSON(jsonArea);
    	return Action.NONE;
    }
    
    private void printJSON(JSONArray jsonArr) {
		HttpServletResponse response = ServletActionContext.getResponse();
    	response.setCharacterEncoding("utf-8");
    	try {
			response.getWriter().println(jsonArr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    private void printJSON(JSONObject jsonObj) {
  		HttpServletResponse response = ServletActionContext.getResponse();
      	response.setCharacterEncoding("utf-8");
      	try {
  			response.getWriter().println(jsonObj.toString());
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
    /*
     * 添加产品
     */
    public String addCommodity() throws IOException {
		Commodity comm = new Commodity();
	
		//1、设置产品类别
		Category category = new Category();
		category.setId(cate_id);
		comm.setCategory(category);
        //2、设置产品其他属性		
		comm.setName(name);
		comm.setInfo(info);
		comm.setPrice(price);
		comm.setSpecs(specs);
		//3、设置上传产品的农户
		Farmer farmer = (Farmer) ServletActionContext.getRequest().getSession().getAttribute("farmer");
		comm.setFarmer(farmer);
		
		if(image != null && imageFileName != null) {
	        String root = ServletActionContext.getServletContext().getRealPath("/zw/image");
	        
	        InputStream is = new FileInputStream(image);
	        
	        OutputStream os = new FileOutputStream(new File(root, imageFileName));
	        
	        byte[] buffer = new byte[1024];
	        int length = 0;
	        
	        while(-1 != (length = is.read(buffer, 0, buffer.length)))
	        {
	            os.write(buffer);
	        }
	        
	        os.close();
	        is.close();
	        comm.setImage("image/"+imageFileName);
		}
        
		commodityService.add(comm);
		Commodity commodity = commodityService.findByCommId(comm.getId());
		ServletActionContext.getRequest().setAttribute("commodity", commodity);
		return "success";
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getCate_id() {
		return cate_id;
	}
	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}
	public int getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public CommodityService getCommodityService() {
		return commodityService;
	}
	public void setCommodityService(CommodityService commodityService) {
		this.commodityService = commodityService;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
    
}
