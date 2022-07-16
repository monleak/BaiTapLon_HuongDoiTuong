package view.math;


import java.awt.*;

/**
 * AABB: Vị trí giữa 2 hình chữ nhật, hình tròn.
 *
 * TODO: Chia AABBCircle và AABBRectangle
 *
 * <p>
 * <div>AABB collisions:</div>
 * AABB stands for axis-aligned bounding box,
 * a rectangular collision shape aligned to the base axes of the scene,
 * which in 2D aligns to the x and y axis.
 * </p>
 * <p>
 * Being axis-aligned means the rectangular box has no rotation
 * and its edges are parallel to the base axes of the scene
 * (e.g. left and right edge are parallel to the y axis).
 * The fact that these boxes are always aligned to the axes of the scene makes calculations easier.
 * </p>
 *
 * @see <a href="https://learnopengl.com/In-Practice/2D-Game/Collisions/Collision-detection">Link</a>
 */
public class AABB {

    private Vector2f pos;       // Tọa độ góc phải bên trên của hình.
    private float xOffset = 0;  // offsetX
    private float yOffset = 0;  // offsetY
    private float w;            // c.rộng   -> hcn
    private float h;            // c.dài    -> hcn
    private float r;            // b.kính   -> h.tròn   -> chỉ dùng khi là h.tròn
    private int size;           // size = r || max(w, h)

    private float surfaceArea;

    public AABB(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;
        this.surfaceArea = w * h;

        size = Math.max(w, h);
    }

    public AABB(Vector2f pos, int r) {
        this.pos = pos;
        this.r = r;
        this.surfaceArea = (float) Math.PI * (r * r);

        size = r;
    }

    /**
     * Getter
     */
    public Vector2f getPos() { return pos; }

    public float getRadius() { return r; }
    public float getSize() { return size; }
    public float getWidth() { return w; }
    public float getHeight() { return h; }
    public float getSurfaceArea() { return surfaceArea; }

    /**
     * Setter
     */
    public void setBox(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public void setCircle(Vector2f pos, int r) {
        this.pos = pos;
        this.r = r;

        size = r;
    }

    public void setWidth(float f) { w = f; }
    public void setHeight(float f) { h = f; }

    public void setXOffset(float f) { xOffset = f; }
    public void setYOffset(float f) { yOffset = f; }
    public float getXOffset() { return xOffset; }
    public float getYOffset() { return yOffset; }

    public float getCenterX () {
        return pos.getX() + xOffset + w / 2;
    }

    public float getCenterY () {
        return pos.getY() + yOffset + h / 2;
    }

    /**
     * collides: Trả về true nếu 2 hình chạm nhau.
     *
     */
    public boolean collides(AABB bBox) {
        return collides(0, 0, bBox);
    }

//    public boolean collides(float dx, float dy, ArrayList<GameObject> go) {
//        boolean collides = false;
//
//        for(int i = 0; i < go.size(); i++) {
//            collides = collides(dx, dy, go.get(i).getBounds());
//            if(collides) {
////                go.get(i).getImage().restoreDefault();
//                // do sth
//                go.remove(i);
//                return collides;
//            }
//        }
//
//        return collides;
//    }

    public boolean collides(float dx, float dy, AABB bBox) {
        float ax = ((pos.getX() + (xOffset)) + (this.w / 2)) + dx;
        float ay = ((pos.getY() + (yOffset)) + (this.h / 2)) + dy;
        float bx = ((bBox.getPos().getX() + (bBox.getXOffset())) + (bBox.getWidth() / 2));
        float by = ((bBox.getPos().getY() + (bBox.getYOffset())) + (bBox.getHeight() / 2));

        if (Math.abs(ax - bx) < (this.w / 2) + (bBox.getWidth() / 2)) {
            if (Math.abs(ay - by) < (this.h / 2) + (bBox.getHeight() / 2)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Inside: Trả về true nếu điểm ở trong hình.
     */
    public boolean inside(int xp, int yp) {
        if(xp == -1 || yp == - 1) return false;

        int wTemp = (int) this.w;
        int hTemp = (int) this.h;
        int x = (int) this.pos.getX();
        int y = (int) this.pos.getY();

        if(xp < x || yp < y) {
            return false;
        }

        wTemp += x;
        hTemp += y;
        return ((wTemp < x || wTemp > xp) && (hTemp < y || hTemp > yp));
    }

    /**
     * intersect: true nếu hình này chứa hình kia.
     */
    public boolean intersect(AABB aBox)
    {

        if((pos.getX() + xOffset > aBox.getPos().getX() + aBox.getXOffset() + aBox.getSize())
        || (aBox.getPos().getX() + xOffset > pos.getX() + aBox.getXOffset() + aBox.getSize()))
        {
            return false;
        }

        if((pos.getY() + yOffset > aBox.getPos().getY() + aBox.getYOffset() + aBox.getSize())
        || (aBox.getPos().getY() + yOffset > pos.getY() + aBox.getYOffset() + aBox.getSize()))
        {
            return false;
        }

        return true;
    }

    /**
     * colCircle: true nếu this=hcn chạm vào tham số: h.tròn
     */
    public boolean colCircle(AABB circle) {

        float totalRadius = r + circle.getRadius();
        totalRadius *= totalRadius;

        float dx = (pos.getX() + circle.getPos().getX());
        float dy = (pos.getY() + circle.getPos().getY());

        return totalRadius < (dx * dx) + (dy * dy);
    }

    /**
     * H.tròn chạm hcn.
     * this: circle
     * aBox: rectangle
     */
    public boolean colCircleBox(AABB aBox) {

        float dx = Math.max(aBox.getPos().getX() + aBox.getXOffset(), Math.min(pos.getX() + (r / 2), aBox.getPos().getX() + aBox.getXOffset() + aBox.getWidth()));
        float dy = Math.max(aBox.getPos().getY() + aBox.getYOffset(), Math.min(pos.getY() + (r / 2), aBox.getPos().getY() + aBox.getYOffset() + aBox.getHeight()));

        dx = pos.getX() + this.xOffset + (r / 2) - dx;
        dy = pos.getY() + this.yOffset +  (r / 2) - dy;

        if(Math.sqrt(dx * dx + dy * dy) < r / 2) {
            return true;
        }

        return false;
    }

    /**
     * Khoàng cách 2 hcn.
     */
    public float distance(Vector2f other) {
        float dx = pos.getX() - other.getY();
        float dy = pos.getY() - other.getY();
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public AABB merge(AABB other) {
        float minX = Math.min(pos.getX(), other.getPos().getX());
        float minY = Math.min(pos.getY(), other.getPos().getY());

        int maxW = (int) Math.max(w, other.getWidth());
        int maxH = (int) Math.max(h, other.getHeight());

        Vector2f pos = new Vector2f(minX, minY);
        return new AABB(pos, maxW, maxH);
	} 
	
	public String toString() {

		String x = Float.toString(pos.getX());
		String y = Float.toString(pos.getY());
		String w = Float.toString(this.w);
		String h = Float.toString(this.h);

		return "{" + x + ", " + y + " : " + w + ", " + h + "}";
	}

    public void render(Graphics2D g2) {
        g2.drawRect(
                (int) (pos.getX() - xOffset),
                (int) (pos.getY() - yOffset),
                (int) w,
                (int) h
        );
    }
}
