package flatten_2d_vector;

import java.util.*;

/**
 * Created by Xiaotian on 10/6/17.
 */
class Vector2D implements Iterator<Integer> {
    Stack<List<Integer>> in;
    Stack<Integer> out;
    public Vector2D(List<List<Integer>> vec2d) {
        in = new Stack<>();
        out = new Stack<>();
        for (int i = vec2d.size() - 1; i >= 0 ; i--) {
            in.push(vec2d.get(i));
        }
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return out.pop();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (!out.isEmpty()) {
            return true;
        }

        if (!in.isEmpty()) {
            List<Integer> top = in.pop();
            for (int i = top.size() - 1; i >= 0; i--) {
                out.push(top.get(i));
            }
            return hasNext();
        } else {
            return false;
        }
    }

    @Override
    public void remove() {}
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
