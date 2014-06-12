/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak.controllers;

import java.util.ArrayList;
import kinomaniak.beans.ReportData;

/**
 *
 * @author Jakub
 */
public class Reports {
    
    
    BeanManager beanManager;
    private ArrayList<ReportData> rds = new ArrayList<ReportData>();
    private ArrayList<String> reports;

    public BeanManager getBeanManager() {
        return beanManager;
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    public ArrayList<ReportData> getRds() {
        if(rds.isEmpty())
            rds = getBeanManager().getReports();
        return rds;
    }

    public void setRds(ArrayList<ReportData> rds) {
        this.rds = rds;
    }
    @Deprecated
    public ArrayList<String> getReports() {
        if(reports.isEmpty())
            for(ReportData rd: beanManager.getReports())
                reports.add(""+rd.getId()+"|"+rd.getUserId());
        return reports;
    }

    public void setReports(ArrayList<String> reports) {
        this.reports = reports;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
    private String report;

    /**
     * Creates a new instance of Reports
     */
    public Reports() {
    }
    
}
