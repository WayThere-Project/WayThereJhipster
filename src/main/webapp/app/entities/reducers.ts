import customer from 'app/entities/customer/customer.reducer';
import driver from 'app/entities/driver/driver.reducer';
import vehicle from 'app/entities/vehicle/vehicle.reducer';
import location from 'app/entities/location/location.reducer';
import insurance from 'app/entities/insurance/insurance.reducer';
import payment from 'app/entities/payment/payment.reducer';
import rate from 'app/entities/rate/rate.reducer';
import reservation from 'app/entities/reservation/reservation.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  customer,
  driver,
  vehicle,
  location,
  insurance,
  payment,
  rate,
  reservation,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
