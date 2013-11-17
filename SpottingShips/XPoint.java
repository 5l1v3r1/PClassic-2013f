import java.util.ArrayList;


public class XPoint {

	int _x, _y;
	public XPoint(int x, int y) {
		this._x = x;
		this._y = y;
	}
	
	public int distance(XPoint p) {
		int dist = Math.abs(p._x - this._x) + Math.abs(p._y - this._y) - 1;
		return dist;
	}
	
	public XPoint[] fanInDimensions(int x, int y) {
		ArrayList<XPoint> points = new ArrayList<XPoint>();
		if (this._x + 1 < x) points.add(new XPoint(this._x + 1, this._y));
		if (this._x > 0) points.add(new XPoint(this._x - 1, this._y));
		if (this._y > 0) points.add(new XPoint(this._x, this._y - 1));
		if (this._y + 1 < y) points.add(new XPoint(this._x, this._y + 1));
		
		return (XPoint[])points.toArray(new XPoint[points.size()]);
	}
	
	public boolean equals(Object o) {
		XPoint p = (XPoint)o;
		return p._x == this._x && p._y == this._y;
	}
	
}
