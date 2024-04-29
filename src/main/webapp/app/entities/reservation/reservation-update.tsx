import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInsurance } from 'app/shared/model/insurance.model';
import { getEntities as getInsurances } from 'app/entities/insurance/insurance.reducer';
import { ILocation } from 'app/shared/model/location.model';
import { getEntities as getLocations } from 'app/entities/location/location.reducer';
import { ICustomer } from 'app/shared/model/customer.model';
import { getEntities as getCustomers } from 'app/entities/customer/customer.reducer';
import { IDriver } from 'app/shared/model/driver.model';
import { getEntities as getDrivers } from 'app/entities/driver/driver.reducer';
import { IVehicle } from 'app/shared/model/vehicle.model';
import { getEntities as getVehicles } from 'app/entities/vehicle/vehicle.reducer';
import { IRate } from 'app/shared/model/rate.model';
import { getEntities as getRates } from 'app/entities/rate/rate.reducer';
import { IPayment } from 'app/shared/model/payment.model';
import { getEntities as getPayments } from 'app/entities/payment/payment.reducer';
import { IReservation } from 'app/shared/model/reservation.model';
import { getEntity, updateEntity, createEntity, reset } from './reservation.reducer';

export const ReservationUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const insurances = useAppSelector(state => state.insurance.entities);
  const locations = useAppSelector(state => state.location.entities);
  const customers = useAppSelector(state => state.customer.entities);
  const drivers = useAppSelector(state => state.driver.entities);
  const vehicles = useAppSelector(state => state.vehicle.entities);
  const rates = useAppSelector(state => state.rate.entities);
  const payments = useAppSelector(state => state.payment.entities);
  const reservationEntity = useAppSelector(state => state.reservation.entity);
  const loading = useAppSelector(state => state.reservation.loading);
  const updating = useAppSelector(state => state.reservation.updating);
  const updateSuccess = useAppSelector(state => state.reservation.updateSuccess);

  const handleClose = () => {
    navigate('/reservation');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getInsurances({}));
    dispatch(getLocations({}));
    dispatch(getCustomers({}));
    dispatch(getDrivers({}));
    dispatch(getVehicles({}));
    dispatch(getRates({}));
    dispatch(getPayments({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.reservationID !== undefined && typeof values.reservationID !== 'number') {
      values.reservationID = Number(values.reservationID);
    }
    if (values.requestedTime !== undefined && typeof values.requestedTime !== 'number') {
      values.requestedTime = Number(values.requestedTime);
    }
    if (values.startTime !== undefined && typeof values.startTime !== 'number') {
      values.startTime = Number(values.startTime);
    }
    if (values.endTime !== undefined && typeof values.endTime !== 'number') {
      values.endTime = Number(values.endTime);
    }
    if (values.waitTime !== undefined && typeof values.waitTime !== 'number') {
      values.waitTime = Number(values.waitTime);
    }
    if (values.driverRating !== undefined && typeof values.driverRating !== 'number') {
      values.driverRating = Number(values.driverRating);
    }
    if (values.customerRating !== undefined && typeof values.customerRating !== 'number') {
      values.customerRating = Number(values.customerRating);
    }
    if (values.tripCost !== undefined && typeof values.tripCost !== 'number') {
      values.tripCost = Number(values.tripCost);
    }

    const entity = {
      ...reservationEntity,
      ...values,
      insurance: insurances.find(it => it.id.toString() === values.insurance?.toString()),
      pickupLocation: locations.find(it => it.id.toString() === values.pickupLocation?.toString()),
      dropoffLocation: locations.find(it => it.id.toString() === values.dropoffLocation?.toString()),
      customer: customers.find(it => it.id.toString() === values.customer?.toString()),
      driver: drivers.find(it => it.id.toString() === values.driver?.toString()),
      vehicle: vehicles.find(it => it.id.toString() === values.vehicle?.toString()),
      rate: rates.find(it => it.id.toString() === values.rate?.toString()),
      payment: payments.find(it => it.id.toString() === values.payment?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...reservationEntity,
          insurance: reservationEntity?.insurance?.id,
          pickupLocation: reservationEntity?.pickupLocation?.id,
          dropoffLocation: reservationEntity?.dropoffLocation?.id,
          customer: reservationEntity?.customer?.id,
          driver: reservationEntity?.driver?.id,
          vehicle: reservationEntity?.vehicle?.id,
          rate: reservationEntity?.rate?.id,
          payment: reservationEntity?.payment?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="wayThereJhipsterApp.reservation.home.createOrEditLabel" data-cy="ReservationCreateUpdateHeading">
            <Translate contentKey="wayThereJhipsterApp.reservation.home.createOrEditLabel">Create or edit a Reservation</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="reservation-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('wayThereJhipsterApp.reservation.reservationID')}
                id="reservation-reservationID"
                name="reservationID"
                data-cy="reservationID"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.reservation.requestedTime')}
                id="reservation-requestedTime"
                name="requestedTime"
                data-cy="requestedTime"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.reservation.date')}
                id="reservation-date"
                name="date"
                data-cy="date"
                type="date"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.reservation.startTime')}
                id="reservation-startTime"
                name="startTime"
                data-cy="startTime"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.reservation.endTime')}
                id="reservation-endTime"
                name="endTime"
                data-cy="endTime"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.reservation.waitTime')}
                id="reservation-waitTime"
                name="waitTime"
                data-cy="waitTime"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.reservation.driverRating')}
                id="reservation-driverRating"
                name="driverRating"
                data-cy="driverRating"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.reservation.customerRating')}
                id="reservation-customerRating"
                name="customerRating"
                data-cy="customerRating"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.reservation.comment')}
                id="reservation-comment"
                name="comment"
                data-cy="comment"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.reservation.tripCost')}
                id="reservation-tripCost"
                name="tripCost"
                data-cy="tripCost"
                type="text"
              />
              <ValidatedField
                id="reservation-insurance"
                name="insurance"
                data-cy="insurance"
                label={translate('wayThereJhipsterApp.reservation.insurance')}
                type="select"
              >
                <option value="" key="0" />
                {insurances
                  ? insurances.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="reservation-pickupLocation"
                name="pickupLocation"
                data-cy="pickupLocation"
                label={translate('wayThereJhipsterApp.reservation.pickupLocation')}
                type="select"
              >
                <option value="" key="0" />
                {locations
                  ? locations.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="reservation-dropoffLocation"
                name="dropoffLocation"
                data-cy="dropoffLocation"
                label={translate('wayThereJhipsterApp.reservation.dropoffLocation')}
                type="select"
              >
                <option value="" key="0" />
                {locations
                  ? locations.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="reservation-customer"
                name="customer"
                data-cy="customer"
                label={translate('wayThereJhipsterApp.reservation.customer')}
                type="select"
              >
                <option value="" key="0" />
                {customers
                  ? customers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="reservation-driver"
                name="driver"
                data-cy="driver"
                label={translate('wayThereJhipsterApp.reservation.driver')}
                type="select"
              >
                <option value="" key="0" />
                {drivers
                  ? drivers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="reservation-vehicle"
                name="vehicle"
                data-cy="vehicle"
                label={translate('wayThereJhipsterApp.reservation.vehicle')}
                type="select"
              >
                <option value="" key="0" />
                {vehicles
                  ? vehicles.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="reservation-rate"
                name="rate"
                data-cy="rate"
                label={translate('wayThereJhipsterApp.reservation.rate')}
                type="select"
              >
                <option value="" key="0" />
                {rates
                  ? rates.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="reservation-payment"
                name="payment"
                data-cy="payment"
                label={translate('wayThereJhipsterApp.reservation.payment')}
                type="select"
              >
                <option value="" key="0" />
                {payments
                  ? payments.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/reservation" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ReservationUpdate;
