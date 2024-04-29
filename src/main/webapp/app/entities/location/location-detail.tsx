import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './location.reducer';

export const LocationDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const locationEntity = useAppSelector(state => state.location.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="locationDetailsHeading">
          <Translate contentKey="wayThereJhipsterApp.location.detail.title">Location</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{locationEntity.id}</dd>
          <dt>
            <span id="locationID">
              <Translate contentKey="wayThereJhipsterApp.location.locationID">Location ID</Translate>
            </span>
          </dt>
          <dd>{locationEntity.locationID}</dd>
          <dt>
            <span id="landmarkName">
              <Translate contentKey="wayThereJhipsterApp.location.landmarkName">Landmark Name</Translate>
            </span>
          </dt>
          <dd>{locationEntity.landmarkName}</dd>
          <dt>
            <span id="addressLine1">
              <Translate contentKey="wayThereJhipsterApp.location.addressLine1">Address Line 1</Translate>
            </span>
          </dt>
          <dd>{locationEntity.addressLine1}</dd>
          <dt>
            <span id="addressLine2">
              <Translate contentKey="wayThereJhipsterApp.location.addressLine2">Address Line 2</Translate>
            </span>
          </dt>
          <dd>{locationEntity.addressLine2}</dd>
          <dt>
            <span id="city">
              <Translate contentKey="wayThereJhipsterApp.location.city">City</Translate>
            </span>
          </dt>
          <dd>{locationEntity.city}</dd>
          <dt>
            <span id="state">
              <Translate contentKey="wayThereJhipsterApp.location.state">State</Translate>
            </span>
          </dt>
          <dd>{locationEntity.state}</dd>
          <dt>
            <span id="country">
              <Translate contentKey="wayThereJhipsterApp.location.country">Country</Translate>
            </span>
          </dt>
          <dd>{locationEntity.country}</dd>
          <dt>
            <span id="zipCode">
              <Translate contentKey="wayThereJhipsterApp.location.zipCode">Zip Code</Translate>
            </span>
          </dt>
          <dd>{locationEntity.zipCode}</dd>
        </dl>
        <Button tag={Link} to="/location" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/location/${locationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LocationDetail;
