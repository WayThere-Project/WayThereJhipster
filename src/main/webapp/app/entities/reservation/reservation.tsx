import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './reservation.reducer';

export const Reservation = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const reservationList = useAppSelector(state => state.reservation.entities);
  const loading = useAppSelector(state => state.reservation.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="reservation-heading" data-cy="ReservationHeading">
        <Translate contentKey="wayThereJhipsterApp.reservation.home.title">Reservations</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="wayThereJhipsterApp.reservation.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/reservation/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="wayThereJhipsterApp.reservation.home.createLabel">Create new Reservation</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {reservationList && reservationList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="wayThereJhipsterApp.reservation.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('reservationID')}>
                  <Translate contentKey="wayThereJhipsterApp.reservation.reservationID">Reservation ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('reservationID')} />
                </th>
                <th className="hand" onClick={sort('requestedTime')}>
                  <Translate contentKey="wayThereJhipsterApp.reservation.requestedTime">Requested Time</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('requestedTime')} />
                </th>
                <th className="hand" onClick={sort('date')}>
                  <Translate contentKey="wayThereJhipsterApp.reservation.date">Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('date')} />
                </th>
                <th className="hand" onClick={sort('startTime')}>
                  <Translate contentKey="wayThereJhipsterApp.reservation.startTime">Start Time</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('startTime')} />
                </th>
                <th className="hand" onClick={sort('endTime')}>
                  <Translate contentKey="wayThereJhipsterApp.reservation.endTime">End Time</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('endTime')} />
                </th>
                <th className="hand" onClick={sort('waitTime')}>
                  <Translate contentKey="wayThereJhipsterApp.reservation.waitTime">Wait Time</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('waitTime')} />
                </th>
                <th className="hand" onClick={sort('driverRating')}>
                  <Translate contentKey="wayThereJhipsterApp.reservation.driverRating">Driver Rating</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('driverRating')} />
                </th>
                <th className="hand" onClick={sort('customerRating')}>
                  <Translate contentKey="wayThereJhipsterApp.reservation.customerRating">Customer Rating</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('customerRating')} />
                </th>
                <th className="hand" onClick={sort('comment')}>
                  <Translate contentKey="wayThereJhipsterApp.reservation.comment">Comment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('comment')} />
                </th>
                <th className="hand" onClick={sort('tripCost')}>
                  <Translate contentKey="wayThereJhipsterApp.reservation.tripCost">Trip Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tripCost')} />
                </th>
                <th>
                  <Translate contentKey="wayThereJhipsterApp.reservation.insurance">Insurance</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="wayThereJhipsterApp.reservation.pickupLocation">Pickup Location</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="wayThereJhipsterApp.reservation.dropoffLocation">Dropoff Location</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="wayThereJhipsterApp.reservation.customer">Customer</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="wayThereJhipsterApp.reservation.driver">Driver</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="wayThereJhipsterApp.reservation.vehicle">Vehicle</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="wayThereJhipsterApp.reservation.rate">Rate</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="wayThereJhipsterApp.reservation.payment">Payment</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {reservationList.map((reservation, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/reservation/${reservation.id}`} color="link" size="sm">
                      {reservation.id}
                    </Button>
                  </td>
                  <td>{reservation.reservationID}</td>
                  <td>{reservation.requestedTime}</td>
                  <td>{reservation.date ? <TextFormat type="date" value={reservation.date} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{reservation.startTime}</td>
                  <td>{reservation.endTime}</td>
                  <td>{reservation.waitTime}</td>
                  <td>{reservation.driverRating}</td>
                  <td>{reservation.customerRating}</td>
                  <td>{reservation.comment}</td>
                  <td>{reservation.tripCost}</td>
                  <td>
                    {reservation.insurance ? <Link to={`/insurance/${reservation.insurance.id}`}>{reservation.insurance.id}</Link> : ''}
                  </td>
                  <td>
                    {reservation.pickupLocation ? (
                      <Link to={`/location/${reservation.pickupLocation.id}`}>{reservation.pickupLocation.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {reservation.dropoffLocation ? (
                      <Link to={`/location/${reservation.dropoffLocation.id}`}>{reservation.dropoffLocation.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{reservation.customer ? <Link to={`/customer/${reservation.customer.id}`}>{reservation.customer.id}</Link> : ''}</td>
                  <td>{reservation.driver ? <Link to={`/driver/${reservation.driver.id}`}>{reservation.driver.id}</Link> : ''}</td>
                  <td>{reservation.vehicle ? <Link to={`/vehicle/${reservation.vehicle.id}`}>{reservation.vehicle.id}</Link> : ''}</td>
                  <td>{reservation.rate ? <Link to={`/rate/${reservation.rate.id}`}>{reservation.rate.id}</Link> : ''}</td>
                  <td>{reservation.payment ? <Link to={`/payment/${reservation.payment.id}`}>{reservation.payment.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/reservation/${reservation.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/reservation/${reservation.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/reservation/${reservation.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="wayThereJhipsterApp.reservation.home.notFound">No Reservations found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Reservation;
