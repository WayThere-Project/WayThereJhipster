import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './reservation.reducer';

export const ReservationDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const reservationEntity = useAppSelector(state => state.reservation.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="reservationDetailsHeading">
          <Translate contentKey="wayThereJhipsterApp.reservation.detail.title">Reservation</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{reservationEntity.id}</dd>
          <dt>
            <span id="reservationID">
              <Translate contentKey="wayThereJhipsterApp.reservation.reservationID">Reservation ID</Translate>
            </span>
          </dt>
          <dd>{reservationEntity.reservationID}</dd>
          <dt>
            <span id="requestedTime">
              <Translate contentKey="wayThereJhipsterApp.reservation.requestedTime">Requested Time</Translate>
            </span>
          </dt>
          <dd>{reservationEntity.requestedTime}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="wayThereJhipsterApp.reservation.date">Date</Translate>
            </span>
          </dt>
          <dd>
            {reservationEntity.date ? <TextFormat value={reservationEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="startTime">
              <Translate contentKey="wayThereJhipsterApp.reservation.startTime">Start Time</Translate>
            </span>
          </dt>
          <dd>{reservationEntity.startTime}</dd>
          <dt>
            <span id="endTime">
              <Translate contentKey="wayThereJhipsterApp.reservation.endTime">End Time</Translate>
            </span>
          </dt>
          <dd>{reservationEntity.endTime}</dd>
          <dt>
            <span id="waitTime">
              <Translate contentKey="wayThereJhipsterApp.reservation.waitTime">Wait Time</Translate>
            </span>
          </dt>
          <dd>{reservationEntity.waitTime}</dd>
          <dt>
            <span id="driverRating">
              <Translate contentKey="wayThereJhipsterApp.reservation.driverRating">Driver Rating</Translate>
            </span>
          </dt>
          <dd>{reservationEntity.driverRating}</dd>
          <dt>
            <span id="customerRating">
              <Translate contentKey="wayThereJhipsterApp.reservation.customerRating">Customer Rating</Translate>
            </span>
          </dt>
          <dd>{reservationEntity.customerRating}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="wayThereJhipsterApp.reservation.comment">Comment</Translate>
            </span>
          </dt>
          <dd>{reservationEntity.comment}</dd>
          <dt>
            <span id="tripCost">
              <Translate contentKey="wayThereJhipsterApp.reservation.tripCost">Trip Cost</Translate>
            </span>
          </dt>
          <dd>{reservationEntity.tripCost}</dd>
          <dt>
            <Translate contentKey="wayThereJhipsterApp.reservation.insurance">Insurance</Translate>
          </dt>
          <dd>{reservationEntity.insurance ? reservationEntity.insurance.id : ''}</dd>
          <dt>
            <Translate contentKey="wayThereJhipsterApp.reservation.pickupLocation">Pickup Location</Translate>
          </dt>
          <dd>{reservationEntity.pickupLocation ? reservationEntity.pickupLocation.id : ''}</dd>
          <dt>
            <Translate contentKey="wayThereJhipsterApp.reservation.dropoffLocation">Dropoff Location</Translate>
          </dt>
          <dd>{reservationEntity.dropoffLocation ? reservationEntity.dropoffLocation.id : ''}</dd>
          <dt>
            <Translate contentKey="wayThereJhipsterApp.reservation.customer">Customer</Translate>
          </dt>
          <dd>{reservationEntity.customer ? reservationEntity.customer.id : ''}</dd>
          <dt>
            <Translate contentKey="wayThereJhipsterApp.reservation.driver">Driver</Translate>
          </dt>
          <dd>{reservationEntity.driver ? reservationEntity.driver.id : ''}</dd>
          <dt>
            <Translate contentKey="wayThereJhipsterApp.reservation.vehicle">Vehicle</Translate>
          </dt>
          <dd>{reservationEntity.vehicle ? reservationEntity.vehicle.id : ''}</dd>
          <dt>
            <Translate contentKey="wayThereJhipsterApp.reservation.rate">Rate</Translate>
          </dt>
          <dd>{reservationEntity.rate ? reservationEntity.rate.id : ''}</dd>
          <dt>
            <Translate contentKey="wayThereJhipsterApp.reservation.payment">Payment</Translate>
          </dt>
          <dd>{reservationEntity.payment ? reservationEntity.payment.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/reservation" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/reservation/${reservationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ReservationDetail;
