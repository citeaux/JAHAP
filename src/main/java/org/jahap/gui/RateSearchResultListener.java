/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.gui;

import java.util.EventListener;

/**
 *
 * @author russ
 */
interface RateSearchResultListener extends EventListener{
    void idinfo(RateSearchResultEvent e);
}