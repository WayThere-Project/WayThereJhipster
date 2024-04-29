import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './vehicle.reducer';

export const VehicleDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const vehicleEntity = useAppSelector(state => state.vehicle.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="vehicleDetailsHeading">
          <Translate contentKey="wayThereJhipsterApp.vehicle.detail.title">Vehicle</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{vehicleEntity.id}</dd>
          <dt>
            <span id="vehicleID">
              <Translate contentKey="wayThereJhipsterApp.vehicle.vehicleID">Vehicle ID</Translate>
            </span>
          </dt>
          <dd>{vehicleEntity.vehicleID}</dd>
          <dt>
            <span id="vehicleSize">
              <Translate contentKey="wayThereJhipsterApp.vehicle.vehicleSize">Vehicle Size</Translate>
            </span>
          </dt>
          <dd>{vehicleEntity.vehicleSize}</dd>
          <dt>
            <span id="make">
              <Translate contentKey="wayThereJhipsterApp.vehicle.make">Make</Translate>
            </span>
          </dt>
          <dd>{vehicleEntity.make}</dd>
          <dt>
            <span id="lastServicingDate">
              <Translate contentKey="wayThereJhipsterApp.vehicle.lastServicingDate">Last Servicing Date</Translate>
            </span>
          </dt>
          <dd>
            {vehicleEntity.lastServicingDate ? (
              <TextFormat value={vehicleEntity.lastServicingDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="model">
              <Translate contentKey="wayThereJhipsterApp.vehicle.model">Model</Translate>
            </span>
          </dt>
          <dd>{vehicleEntity.model}</dd>
        </dl>
        <Button tag={Link} to="/vehicle" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/vehicle/${vehicleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default VehicleDetail;
