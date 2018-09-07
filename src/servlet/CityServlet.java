package servlet;

import dao.ProvinceDao;
import domain.City;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CityServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProvinceDao provinceDao = new ProvinceDao();
        List<City> cities;

        int pid = Integer.parseInt(req.getParameter("pid"));
        cities = provinceDao.findByProvinceId(pid);

        JSONArray jsonArray = JSONArray.fromObject(cities);

        resp.getWriter().print(jsonArray);
        System.out.println(jsonArray);
    }
}
