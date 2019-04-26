/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.util;

import com.google.gson.Gson;
import java.io.File;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class ParametersReader {

    private final HttpServletRequest request;
    private final boolean multipart;
    private Map<String, List<FileItem>> parseParameterMap;

    public ParametersReader(HttpServletRequest request) throws FileUploadException {
        this.request = request;
        multipart = ServletFileUpload.isMultipartContent(request);
        if (multipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            parseParameterMap = upload.parseParameterMap(request);
        }
    }

    public String saveFile(String formName, String location) throws Exception {
        List<String> savedFiles = saveFiles(formName, location);
        if (savedFiles != null && savedFiles.size() > 0) {
            return savedFiles.iterator().next();
        }
        return null;
    }

    public List<String> saveFiles(String name, String location) throws Exception {
        List<String> result = new LinkedList<>();
        for (FileItem item : parseParameterMap.get(name)) {
            String fileName = item.getName();
            String root = request.getServletContext().getRealPath("/");
            File uploadDir = new File(root + File.separator + location
                    + File.separator);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuid = UUID.randomUUID().toString();
            item.write(new File(uploadDir, uuid));
            result.add(uuid);
        }
        return result;
    }

    public String getString(String name, String defaultValue) {
        String param = request.getParameter(name);
        if (multipart) {
            if (parseParameterMap.get(name) != null
                    && parseParameterMap.get(name).size() > 0) {
                FileItem item = parseParameterMap.get(name).get(0);
                if (item.isFormField()) {
                    param = item.getString();
                }
            }
        }
        return param == null || param.isEmpty()
                ? defaultValue : param;
    }

    public String getString(String name) {
        return getString(name, null);
    }

    public int getInteger(String name, int defaultValue) {
        String param = getString(name, String.valueOf(defaultValue)).trim();
        return param == null || param.isEmpty()
                ? defaultValue : Integer.parseInt(param);
    }

    public BigDecimal getDecimal(String name) {
        return getDecimal(name, null);
    }

    public BigDecimal getDecimal(String name, BigDecimal defaultValue) {
        String param = getString(name, String.valueOf(defaultValue)).trim();
        return param == null || param.isEmpty()
                ? defaultValue : new BigDecimal(param);
    }

    public <T> T getJsonObject(String name, Class<T> objectClass) {
        Gson gson = new Gson();
        String param = getString(name, null);
        return param == null || param.isEmpty()
                ? null : gson.fromJson(param, objectClass);
    }

    public <T> List<T> getJsonList(String name, Type type) {
        Gson gson = new Gson();
        String param = getString(name, null);
        return param == null || param.isEmpty()
                ? null : gson.fromJson(param, type);
    }
}
