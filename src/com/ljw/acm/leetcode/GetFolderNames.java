package com.ljw.acm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author junwei.liang01@ucarinc.com
 * @date 2020/6/24 18:08
 */
public class GetFolderNames {
    class M {
        int sum;
        int curr;
    }

    public String[] getFolderNames(String[] names) {
        Map<String, M> map = new HashMap<>(names.length);
        for (int i = 0; i < names.length; i++) {
            M m = map.get(names[i]);
            if (m == null) {
                map.put(names[i], new M());
            } else {
                m.sum += 1;
            }
        }
        for (int i = 0; i < names.length; i++) {
            M m = map.get(names[i]);
            if (m.sum > 0 && m.curr > 0) {
                String temp = names[i] + "(" + m.curr + ")";
                while (map.get(temp) != null) {
                    m.curr++;
                    temp = names[i] + "(" + m.curr + ")";
                }
                names[i] = temp;
            }
            m.curr++;
        }
        return names;
    }
}
