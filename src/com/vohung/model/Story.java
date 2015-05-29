package com.vohung.model;

import java.io.Serializable;

public class Story implements Serializable {
	private String name;
	private String content;
	private String source;
	private String type;
	private int scheduled_ts;
	private String seed_id;
	private String ts;
	private String iid;
	private String ats;
	private String slug;
	private CounterStory counterStory;
	private String is_iphone;
	private User u_port;
	private String id;
	private String ytid;
	private ImagesStory image;
	public Story() {
		super();
	}
	
	



	public Story(String name, String iid, String id,
			String ytid, ImagesStory image) {
		super();
		this.name = name;
		this.iid = iid;
		this.id = id;
		this.ytid = ytid;
		this.image = image;
	}



	public User getU_port() {
		return u_port;
	}



	public void setU_port(User u_port) {
		this.u_port = u_port;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getYtid() {
		return ytid;
	}



	public void setYtid(String ytid) {
		this.ytid = ytid;
	}



	public Story(String name, String content, String source, String type,
			int scheduled_ts, String seed_id, String ts, String iid,
			String ats, String slug, CounterStory counterStory,
			String is_iphone, ImagesStory image) {
		super();
		this.name = name;
		this.content = content;
		this.source = source;
		this.type = type;
		this.scheduled_ts = scheduled_ts;
		this.seed_id = seed_id;
		this.ts = ts;
		this.iid = iid;
		this.ats = ats;
		this.slug = slug;
		this.counterStory = counterStory;
		this.is_iphone = is_iphone;
		this.image = image;
	}

	public Story(String name, String content, String type, String seed_id,
			String ts, String iid, String ats, String slug,
			CounterStory counterStory, String is_iphone) {
		super();
		this.name = name;
		this.content = content;
		this.type = type;
		this.seed_id = seed_id;
		this.ts = ts;
		this.iid = iid;
		this.ats = ats;
		this.slug = slug;
		this.counterStory = counterStory;
		this.is_iphone = is_iphone;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getScheduled_ts() {
		return scheduled_ts;
	}

	public void setScheduled_ts(int scheduled_ts) {
		this.scheduled_ts = scheduled_ts;
	}

	public ImagesStory getImage() {
		return image;
	}

	public void setImage(ImagesStory image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSeed_id() {
		return seed_id;
	}
	public void setSeed_id(String seed_id) {
		this.seed_id = seed_id;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getAts() {
		return ats;
	}
	public void setAts(String ats) {
		this.ats = ats;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public CounterStory getCounterStory() {
		return counterStory;
	}
	public void setCounterStory(CounterStory counterStory) {
		this.counterStory = counterStory;
	}
	public String getIs_iphone() {
		return is_iphone;
	}
	public void setIs_iphone(String is_iphone) {
		this.is_iphone = is_iphone;
	}
	
	

}
