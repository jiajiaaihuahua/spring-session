package online.shixun.project;

import java.io.Serializable;

/**
 * 商品类
 *
 */
public class Goods implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;			//id
	private String goodsname;	//商品名称
	private String category; 	//品类
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Goods(long id, String goodsname, String category) {
		super();
		this.id = id;
		this.goodsname = goodsname;
		this.category = category;
	}
	public Goods() {
		super();
	}
	
	
}
