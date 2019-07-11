package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Semantic")
public enum Semantic {
	Init,
	SaveEnabled,
	DeleteEnabled,
	Final
}
