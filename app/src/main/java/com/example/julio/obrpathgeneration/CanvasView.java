package com.example.julio.obrpathgeneration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.util.Set;
import java.util.Vector;

import Graph.Graph;
import Graph.Vertex;

/**
 * Created by Julio on 03/08/2015.
 */
public class CanvasView extends View {
    //DefaultMutableTreeNode t;
    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    Context context;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;
    private Graph<TrackNode , Object> trackRepresentation;

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);


        this.trackRepresentation = createGraph();

        context = c;

        // we set a new Path
        mPath = new Path();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

    }

    public Graph<TrackNode, Object> createGraph(){

        Graph<TrackNode, Object> g;
        g = new Graph<TrackNode, Object> ();

        TrackNode v1 = new TrackNode(Math.random(),Math.random());
        TrackNode v2 = new TrackNode(Math.random(), Math.random());
        TrackNode v3 = new TrackNode(Math.random(),Math.random());
        TrackNode v4 = new TrackNode(Math.random(),Math.random());


        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);



        return g;
    }

    public void drawPath(int w, int h){

        Vector< Vertex<TrackNode> > vertexes = trackRepresentation.vertexes;



        System.out.println("Draw path");
        Vertex<TrackNode> first = vertexes.firstElement();
        Vertex<TrackNode> last = vertexes.firstElement();
        mPath.moveTo((float) (w * first.value.posx), (float) (h * first.value.posy));
        for ( Vertex<TrackNode> v : vertexes){

            TrackNode t = v.value;
            mPath.addCircle((float) (w * t.posx), (float) (h * t.posx), 10, Path.Direction.CCW );
            if(v != first) {
                //mPath.moveTo((float) (w * last.value.posx), (float) (h * last.value.posy));
                mPath.lineTo((float) (w * t.posx), (float) (h * t.posx)); //draw
            }
            last = v;


            //Set<DefaultEdge> edges = trackRepresentation.edgesOf(t);




        }
        invalidate();

    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        drawPath(w,h);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw the mPath with the mPaint on the canvas when onDraw
        canvas.drawPath(mPath, mPaint);
    }

    // when ACTION_DOWN start touch according to the x,y values
    private void startTouch(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    // when ACTION_MOVE move touch according to the x,y values
    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas() {
        mPath.reset();
        invalidate();
    }

    // when ACTION_UP stop touch
    private void upTouch() {
        mPath.lineTo(mX, mY);
    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }
}
