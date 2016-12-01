package rs.uns.acs.ftn.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CartItem implements Serializable{

	private String productId;
	private String productName;
	private Integer QTY;
	private Double productPrice;
	
	public CartItem(){
		
	}

	public CartItem(String productId, String productName, Integer qTY, Double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		QTY = qTY;
		this.productPrice = productPrice;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQTY() {
		return QTY;
	}

	public void setQTY(Integer qTY) {
		QTY = qTY;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productPrice == null) ? 0 : productPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productPrice == null) {
			if (other.productPrice != null)
				return false;
		} else if (!productPrice.equals(other.productPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CartItem [productId=" + productId + ", productName=" + productName + ", QTY=" + QTY + ", productPrice="
				+ productPrice + "]";
	}
}
