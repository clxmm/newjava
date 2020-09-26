package i.test6zhurubean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/9/19 8:38 下午
 */
public class Beano {


    private AnotherBean anotherBean;

    private String string;


    private AnotherBean anotherBean1;
    private String string1;


    private List<String> stringList;
    private List<AnotherBean> anotherBeanList;
    Map<String, String> stringMap;
    Map<AnotherBean,AnotherBean> anotherBeanMap;
    Set<String> stringSet;
    Set<AnotherBean> anotherBeanSet;

    private Properties properties;


    private AnotherBean anotherBean2;

    public AnotherBean getAnotherBean2() {
        return anotherBean2;
    }

    public void setAnotherBean2(AnotherBean anotherBean2) {
        this.anotherBean2 = anotherBean2;
    }

    public Beano(AnotherBean anotherBean, String string) {
        this.anotherBean = anotherBean;
        this.string = string;
    }

    public AnotherBean getAnotherBean() {
        return anotherBean;
    }

    public void setAnotherBean(AnotherBean anotherBean) {
        this.anotherBean = anotherBean;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }


    public AnotherBean getAnotherBean1() {
        return anotherBean1;
    }

    public void setAnotherBean1(AnotherBean anotherBean1) {
        this.anotherBean1 = anotherBean1;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<AnotherBean> getAnotherBeanList() {
        return anotherBeanList;
    }

    public void setAnotherBeanList(List<AnotherBean> anotherBeanList) {
        this.anotherBeanList = anotherBeanList;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public Map<AnotherBean, AnotherBean> getAnotherBeanMap() {
        return anotherBeanMap;
    }

    public void setAnotherBeanMap(Map<AnotherBean, AnotherBean> anotherBeanMap) {
        this.anotherBeanMap = anotherBeanMap;
    }

    public Set<String> getStringSet() {
        return stringSet;
    }

    public void setStringSet(Set<String> stringSet) {
        this.stringSet = stringSet;
    }

    public Set<AnotherBean> getAnotherBeanSet() {
        return anotherBeanSet;
    }

    public void setAnotherBeanSet(Set<AnotherBean> anotherBeanSet) {
        this.anotherBeanSet = anotherBeanSet;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Beano{" +
                "anotherBean=" + anotherBean +
                ", string='" + string + '\'' +
                ", anotherBean1=" + anotherBean1 +
                ", string1='" + string1 + '\'' +
                ", stringList=" + stringList +
                ", anotherBeanList=" + anotherBeanList +
                ", stringMap=" + stringMap +
                ", anotherBeanMap=" + anotherBeanMap +
                ", stringSet=" + stringSet +
                ", anotherBeanSet=" + anotherBeanSet +
                ", properties=" + properties +
                '}';
    }
}
