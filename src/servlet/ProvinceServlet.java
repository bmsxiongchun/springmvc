package servlet;

import dao.ProvinceDao;
import domain.Province;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProvinceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProvinceDao provinceDao = new ProvinceDao();

        List<Province> provinces = provinceDao.findAllProvince();
        JSONArray jsonArray = JSONArray.fromObject(provinces);

        System.out.println(jsonArray);
        resp.getWriter().print(jsonArray);
    }
}
