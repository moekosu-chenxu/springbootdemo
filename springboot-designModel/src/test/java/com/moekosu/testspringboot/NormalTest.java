package com.moekosu.testspringboot;

import org.junit.Test;

/**
 * @author chenxu
 * @date 2018/01
 */
public class NormalTest {

    interface bizProxy{
        void process();
    }
    interface ActProxy {
        bizProxy getProxy();
    }
    class commonBiz implements bizProxy{
        private ActProxy act = null;
        public commonBiz(ActProxy act){
            this.act = act;
        }
        public void process() {
            System.out.println("commonBiz.process");
        }
    }
    class bizA implements bizProxy{
        public void process() {
            System.out.println("bizA.process");
        }
    }

    @Test
    public void test()
    {
        bizProxy biz = new commonBiz(new ActProxy(){
            public bizProxy getProxy(){
                return new bizA();
            }
        });
    }

}
