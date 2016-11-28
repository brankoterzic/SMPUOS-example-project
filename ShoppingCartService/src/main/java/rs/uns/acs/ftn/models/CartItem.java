package rs.uns.acs.ftn.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CartItem implements Serializable{

	private String id;
	private String name;
	private Integer QTY;
	private Double price;
	
	public CartItem(){
		
	}

	public CartItem(String id, String name, Integer qTY, Double price) {
		super();
		this.id = id;
		this.name = name;
		QTY = qTY;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQTY() {
		return QTY;
	}

	public void setQTY(Integer qTY) {
		QTY = qTY;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((QTY == null) ? 0 : QTY.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		if (QTY == null) {
			if (other.QTY != null)
				return false;
		} else if (!QTY.equals(other.QTY))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", name=" + name + ", QTY=" + QTY + ", price=" + price + "]";
	}

	
}
