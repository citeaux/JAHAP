/*
 * The MIT License
 *
 * Copyright 2014 Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jahap.entities.res;

import java.util.Date;
import org.jahap.entities.acc.Accounts;
import org.jahap.entities.base.Address;
import org.jahap.entities.base.Rooms;

/**
 *
 * @author russ
 */
public interface occ_ie {

    Date getArrivaldate();

    Date getArrivaltime();

    Date getDeparturedate();

    Date getDeparturetime();

    Res getRes();

    Accounts getAccount();
    
    Rooms getRoom();
    
    Address getGuest();
    
    Housekeepingblock getHousekeepingblock();
    
    Maintenanceblock getMaintenanceblock();
	    

    void setArrivaldate(Date arrivaldate);

    void setArrivaltime(Date arrivaltime);

    void setDeparturedate(Date departuredate);

    void setDeparturetime(Date departuretime);

    void setRes(Res res);
    
    void setHousekeepingblock(Housekeepingblock housekeepingblock);
    
    void setMaintenanceblock(Maintenanceblock maintenanceblock); 

    void setAccount(Accounts account);
    
    void setRoom(Rooms room);
    
    void setGuest(Address guest);
}
